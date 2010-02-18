package me.hcl.seekin.Util

import org.springframework.context.i18n.LocaleContextHolder as LCH
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

class DateService {

    def test() {
      Date.metaClass.formatDate = { ->

          def locale = LCH.getLocale()
          def pattern
          if(locale.toString() == "fr") {
              pattern = "dd/MM/yyyy"
          }
          else {
              pattern = "MM/dd/yyyy"
          }
          def formatter = new SimpleDateFormat(pattern, locale);
 
          return formatter.format(delegate)
      }
    }

    def formatDate(request, date) {

          return "blabla"
    }

}