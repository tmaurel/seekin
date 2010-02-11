package me.hcl.seekin.Auth.Role

import java.io.File
import java.lang.ClassLoader
import java.net.URL

/**
 * Service that scan the folder with the domain class Role and build a list of the different ROLE
 */
class RoleService {

    def listRole = []

    def nameRole = {

       def pckgname = "me.hcl.seekin.Auth.Role"
       ArrayList<Class> classes = new ArrayList<Class>()
       // Get a File object for the package
       File directory = null
       ClassLoader cld = Thread.currentThread().getContextClassLoader()
       String path = pckgname.replace('.', '/')
       URL resource = cld.getResource(path)
       directory = new File(resource.getFile())
       String[] files = directory.list()
       files.each {
         if(it.lastIndexOf('$') == -1 && !(it =~ "Role") && !(it =~ "Controller") && !(it =~ "Test")) {
           listRole.add(it.substring(0, it.length() - 6));
         }
       }

       return listRole

    }



}
