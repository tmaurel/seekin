import me.hcl.seekin.Formation.*
import java.text.DateFormat
import java.text.SimpleDateFormat

DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

fixture {

        m2008(Millesime, begin:df.parse("01/09/2008"), blabla:df.parse("30/08/2009"))
        m2009(Millesime, begin:df.parse("01/09/2009"), blabla:df.parse("30/08/2010"))
        m2010(Millesime, begin:df.parse("01/09/2010"), blabla:df.parse("30/08/2011"))

}
