package me.hcl.seekin.Message

import grails.converters.JSON
import org.hibernate.LockMode
import me.hcl.seekin.Auth.User

class MessageController {

    def authenticateService
    def sessionFactory

    def index = { }

    def show = { 
        if (authenticateService.isLoggedIn())
        {
            // Get the user instance logged in
            def userInstance = authenticateService.userDomain()

            def copyInstance = MessageCopy.get(params.id)

            if (!copyInstance) {
                flash.message = "message.not.found"
                redirect(controller:"user", action: "index")
            }
            else {

                def messageInstance = copyInstance.message

                if(messageInstance.author.id == userInstance.id || messageInstance.recipients.id.contains(userInstance.id))
                {
                    if(copyInstance.status == MessageCopy.MESSAGE_UNREAD)
                    {
                        copyInstance.status = MessageCopy.MESSAGE_READ
                        copyInstance.save()
                        
                    }
                    return [messageInstance: messageInstance]
                }
                else
                {
                    flash.message = "message.denied"
                    redirect(controller:"user", action: "index")
                }
            }

        }
        else
            redirect(controller:"user", action:"auth")
    }

    def write = {

        if (authenticateService.isLoggedIn())
        {
            // Get the user instance logged in
            def userInstance = authenticateService.userDomain()
            def messageInstance = new Message()

            if(params.id)
            {
                def recipient = User.get(params.id)
                if(recipient)
                {
                    messageInstance.addToRecipients(recipient)
                }
            }

            return [messageInstance: messageInstance]
        }
    }

    def reply = {

        if (authenticateService.isLoggedIn())
        {
            // Get the user instance logged in
            def userInstance = authenticateService.userDomain()
            def messageInstance = new Message()

            if(params.id)
            {
                def cmessage = Message.get(params.id)

                if(cmessage?.recipients?.id?.contains(userInstance.id))
                {

                    messageInstance.addToRecipients(cmessage.author)
                    messageInstance.subject = "Re : " + cmessage.subject
                    messageInstance.body = """
\n\n\n${g.renderOriginalMessageStart()}
${message(code:'message.from')} : ${cmessage.author}
${cmessage.body}
${g.renderOriginalMessageEnd()}"""
                }

            }
            render view: 'write', model: [messageInstance: messageInstance]
        }
    }

    def save = {
        if (authenticateService.isLoggedIn())
        {
            // Get the user instance logged in
            def userInstance = authenticateService.userDomain()

            def messageInstance = new Message()
            messageInstance.subject = params.subject
            messageInstance.body = params.body
            messageInstance.author = userInstance
            def rec
            if(params.recipients?.class?.isArray())
            {
                params.recipients?.each {
                    rec = User.get(it.toInteger())
                    if(rec)
                        messageInstance.addToRecipients(rec)
                }
            }
            else
            {
                rec = User.get(params.recipients?.toInteger())
                    if(rec)
                        messageInstance.addToRecipients(rec)
            }

            if (messageInstance.validate() && !messageInstance.hasErrors()) {

                def errors = 0
                def sentbox = SentBox.findByOwner(userInstance)

                def sent = new MessageCopy(
                                message: messageInstance,
                                box: sentbox,
                                status: MessageCopy.MESSAGE_READ
                            )

                if(sent.save())
                {
                    messageInstance?.recipients?.each {
                        def inbox = InBox.findByOwner(it)
                        if(inbox)
                        {
                            def msg = new MessageCopy(
                                            message: messageInstance,
                                            box: inbox
                                        )
                            if(!msg.save())
                                ++errors
                        }
                        else ++errors
                    }
                }
                else ++errors

                if(!errors)
                {
                    flash.message = "message.sent"
                    flash.defaultMessage = "Message sent"
                    redirect(action: "show", id: sent.id)
                }
                else
                {
                    flash.message = "message.creation.error"
                    redirect(controller:"user", action: "index")
                }

            }
            else {
                render(view: "write", model: [messageInstance: messageInstance])
            }

        }
    }

    def delete = {

        if (authenticateService.isLoggedIn())
        {
            // Get the user instance logged in
            def userInstance = authenticateService.userDomain()

            def copy

            params.each {
                if(it.key.contains("delete_") && it.value == "on")
                {
                    copy = MessageCopy.get(it.key.split("_")[1].toInteger())
                    if(copy?.box?.owner?.id == userInstance.id)
                    {
                        def trash = TrashBox.findByOwner(userInstance)
                        if(trash)
                        {
                            copy.box.removeFromMessages(copy)
                            trash.addToMessages(copy)
                            trash.save(flush:true)
                        }
                    }
                }
            }

            response.setHeader("Cache-Control", "no-store")

            def d = MessageBox.createCriteria().list()
            {
                eq('owner', userInstance)

                messages {
                    projections {
                        count('box', 'count')
                    }
                    eq('status', MessageCopy.MESSAGE_UNREAD)
                }
                groupProperty('id')
            }

            def query = "SELECT messagebox.id, count(message.id)"
            query += " FROM MessageBox as messagebox"
            query += " LEFT OUTER JOIN messagebox.messages as message"
            query += "      WITH message.status = '" + MessageCopy.MESSAGE_UNREAD + "'"
            query += " WHERE messagebox.owner = " + userInstance.id
            query += " GROUP BY messagebox.id"


            def list = sessionFactory.currentSession.createQuery(query).list()

            println list

            def ret = []
            
            list.each {
                def val = [
                    key: it[0],
                    value: it[1]
                ]
                ret << val
            }

            render ret as JSON
        }
    }

    def dataTableDataAsJSON = {

        if (authenticateService.isLoggedIn() && params.idBox != null)
        {
            // Get the user instance logged in
            def userInstance = authenticateService.userDomain()

            def box = MessageBox?.get(params.idBox)
            if(box)
            {
                def list = MessageCopy.createCriteria().list()
                {

                    message {

                        if(params.sort != "author")
                            order(params.sort, params.order)
                        else
                        {
                            author {
                                order("firstName", params.order)
                                order("lastName", params.order)
                            }
                        }
                    }

                    eq('box', box)
                    maxResults(params.max?.toInteger())
                    firstResult(params.offset?.toInteger())

                }

                def count = MessageCopy.countByBox(box)

                def ret = []
                response.setHeader("Cache-Control", "no-store")

                list.each {
                    def unread = (it.status == MessageCopy.MESSAGE_UNREAD)?true:false
                    ret << [
                        delete: it.id,
                        subject: [value: it.message?.subject, unread: unread],
                        author: [value: it.message?.author?.firstName + " " + it.message?.author?.lastName, unread: unread],
                        dateCreated: [value: it.message?.dateCreated.formatDateHour(), unread: unread],
                        urlID: [id: it.id, unread: unread]
                    ]
                }

                def data = [
                        totalRecords: count,
                        results: ret
                ]

                render data as JSON
            }
        }
    }
}
