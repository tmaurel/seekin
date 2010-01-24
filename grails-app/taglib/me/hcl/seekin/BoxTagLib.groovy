package me.hcl.seekin

class BoxTagLib {
	
	def boxStyle = ""
	
	def layoutBox = {
		if(this.boxStyle != "")
		{
			out << "<style type=\"text/css\" media='screen'>\n"
			out << this.boxStyle
			out << "</style>"
			this.boxStyle = ""
		}
	}
	
	def roundedBox = { attrs, body ->
		def random = new Random()
		def boxid = attrs['id'] ?: "box" + random.nextInt(99999 - 10000)
		def width = attrs['width']?:null
		def height = attrs['height']?:null		
		
		if(width && width.isInteger())
		{
			this.boxStyle += "	#" + boxid + " { width: " + width + "px; }\n"
			this.boxStyle += "	#" + boxid + " .box_header .box_header_mid { width: " + (width.toInteger() - 26) + "px; }\n"
			this.boxStyle += "	#" + boxid + " .box_body .box_body_mid { width: " + (width.toInteger() - 22) + "px; }\n"
			this.boxStyle += "	#" + boxid + " .box_footer .box_footer_mid { width: " + (width.toInteger() - 26) + "px; }\n"
		}
		if(height && height.isInteger())
		{
			this.boxStyle += "	#" + boxid + " .box_body { height: " + (height.toInteger() - 24) + "px; }\n"
		}

		out << "<div id=\"" + boxid + "\" class=\"box\">\n"
		out << "	<div class=\"box_header\">\n"
		out << "		<div class=\"box_header_left\"></div>\n"
		out << "		<div class=\"box_header_mid\"></div>\n"
		out << "		<div class=\"box_header_right\"></div>\n"
		out << "	</div>\n"
		out << "	<div class=\"box_body\">\n"
		out << "		<div class=\"box_body_left\"></div>\n"
		out << "		<div class=\"box_body_mid\">" + body() + "</div>\n"
		out << "		<div class=\"box_body_right\"></div>\n"
		out << "	</div>\n"
		out << "	<div class=\"box_footer\">\n"
		out << "		<div class=\"box_footer_left\"></div>\n"
		out << "		<div class=\"box_footer_mid\"></div>\n"
		out << "		<div class=\"box_footer_right\"></div>\n"
		out << "	</div>\n"
		out << "</div>\n"

	}
	
	
	def roundedHeader = { attrs, body ->
		def random = new Random()
		def boxid = attrs['id'] ?: "header" + random.nextInt(99999 - 10000)
		def width = attrs['width']?:null
		def height = attrs['height']?:null
	
		if(width && width.isInteger())
		{
			this.boxStyle += "	#" + boxid + " { width: " + width + "px; }\n"
			this.boxStyle += "	#" + boxid + " .box_header .box_header_mid { width: " + (width.toInteger() - 26) + "px; }\n"
			this.boxStyle += "	#" + boxid + " .box_body .box_body_mid { width: " + (width.toInteger() - 22) + "px; }\n"
			this.boxStyle += "	#" + boxid + " .box_footer .box_footer_mid { width: " + (width.toInteger() - 26) + "px; }\n"
		}
		if(height && height.isInteger())
		{
			this.boxStyle += "	#" + boxid + " { height: " + height + "px; line-height: " + height + "px; }\n"
			this.boxStyle += "	#" + boxid + " .box_body { height: " + (height.toInteger() - 24) + "px; }\n"
		}
		
		out << "<div id=\"" + boxid + "\" class=\"rnd_header_box\">\n"
		out << "	<div class=\"box_highlight\">\n"	
		out << "		<div class=\"box_header\">\n"
		out << "			<div class=\"box_header_left\"></div>\n"
		out << "			<div class=\"box_header_mid\"></div>\n"
		out << "			<div class=\"box_header_right\"></div>\n"
		out << "		</div>\n"
		out << "		<div class=\"box_body\">\n"
		out << "			<div class=\"box_body_left\"></div>\n"
		out << "			<div class=\"box_body_mid\">" + body() + "</div>\n"
		out << "			<div class=\"box_body_right\"></div>\n"
		out << "		</div>\n"
		out << "		<div class=\"box_footer\">\n"
		out << "			<div class=\"box_footer_left\"></div>\n"
		out << "			<div class=\"box_footer_mid\"></div>\n"
		out << "			<div class=\"box_footer_right\"></div>\n"
		out << "		</div>\n"
		out << "	</div>\n"
		out << "</div>\n"
		
	}	
	
	def boxHeader = { attrs, body ->
		def random = new Random()
		def boxid = attrs['id'] ?: "header" + random.nextInt(99999 - 10000)
		def width = attrs['width']?:null
		def height = attrs['height']?:null
		
		if(width && width.isInteger())
		{
			this.boxStyle += "	#" + boxid + " { width: " + (width.toInteger() - 12) + "px; }\n"
		}
		if(height && height.isInteger())
		{
			this.boxStyle += "	#" + boxid + " { height: " + height + "px; line-height: " + height + "px; }\n"
		}
		
		out << "<div id=\"" + boxid + "\" class=\"header_box\">\n"
		out << "	<div class=\"box_highlight\">\n"	
		out << "			" + body() + "\n"
		out << "	</div>\n"
		out << "</div>\n"
		
	}	
	
}
