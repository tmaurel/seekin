package me.hcl.seekin

import org.codehaus.groovy.grails.plugins.springsecurity.Secured
import me.hcl.seekin.Auth.Role.Student


class SecuredController {
   def authenticateService
   
   def index = {

      def principal = authenticateService.principal()
      println principal.getUsername()//get username
      println principal.getAuthorities()//get authorities

      Student.list().each {
          println it.user.email
      }
   }

   @Secured(['ROLE_STUDENT'])
   def student = {

   }
}