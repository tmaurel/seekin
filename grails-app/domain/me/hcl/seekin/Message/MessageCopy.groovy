package me.hcl.seekin.Message

class MessageCopy {


    static final String MESSAGE_UNREAD = 'message.unread'
    static final String MESSAGE_READ = 'message.read'

    static belongsTo = MessageBox

    Message message

    MessageBox box

    String status = MESSAGE_UNREAD

    static constraints = {
        status(blank: false)
    }

}
