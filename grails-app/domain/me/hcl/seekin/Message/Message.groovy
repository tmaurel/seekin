package me.hcl.seekin.Message

import me.hcl.seekin.Auth.User

class Message {

    static hasMany = [copies: MessageCopy, recipients:User]

    static belongsTo = [User, MessageCopy]

    User author

    List recipients

    String subject

    String body

    Date dateCreated

    static constraints = {
        subject(blank: false)
        body(blank: false, maxSize: 5000)
    }

}
