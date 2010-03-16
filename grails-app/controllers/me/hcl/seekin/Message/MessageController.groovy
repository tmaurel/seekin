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
            sessionFactory.currentSession.merge(userInstance)

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
    }


    def addData = {

        if (authenticateService.isLoggedIn())
        {
            // Get the user instance logged in
            def userInstance = authenticateService.userDomain()
            sessionFactory.currentSession.merge(userInstance)

            def inbox = InBox.findByOwner(userInstance)

            def copy = new MessageCopy(
                            message: new Message(
                                author: userInstance,
                                subject: "OLOL",
                                body: "OLOOOOOL"
                            ),
                            box:inbox
                        )
            copy.save()
            
            copy.message.addToRecipients(User.get(1))

        }

    }

    def dataTableDataAsJSON = {

        if (authenticateService.isLoggedIn())
        {
            // Get the user instance logged in
            def userInstance = authenticateService.userDomain()
            sessionFactory.currentSession.merge(userInstance)

            def box = MessageBox.get(params.idBox)

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
