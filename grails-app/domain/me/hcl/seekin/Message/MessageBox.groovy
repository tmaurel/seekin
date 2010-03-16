package me.hcl.seekin.Message

import me.hcl.seekin.Auth.User

class MessageBox {

    static final String MESSAGEBOX_INBOX    = 'messagebox.inbox'
    static final String MESSAGEBOX_SENT     = 'messagebox.sent'
    static final String MESSAGEBOX_TRASH    = 'messagebox.trash'

    static hasMany = [messages: MessageCopy]

    static belongsTo = User

    User owner

    String label = MESSAGEBOX_INBOX

    static constraints = {
        label(nullable: false)
    }

    static mapping = {
        sort "label"
    }

    def getUnreadMessages = {
        def msgs = []
        this.messages.each {
            if(it.status == MessageCopy.MESSAGE_UNREAD)
                msgs << it
        }
        return msgs
    }

    static getUnreadMessagesForUser = { user ->
        def msgs = []
        if(user)
        {
            user.boxes.each {
                it.messages.each {
                    if(it.status == MessageCopy.MESSAGE_UNREAD)
                        msgs << it
                }
            }
        }
        return msgs
    }

}
