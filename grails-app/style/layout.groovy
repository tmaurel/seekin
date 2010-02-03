selectedColor = '#163281'

style = {
	
	'html *' (
		'margin': 0
	)
	
	'body' (
		'background-color':'#fff',
		'height':'100%'
	)

	'a img' (
		'border':'1px solid #000'
	) 
	
	'#header' (
		'width':'100%', 
		'height':'150px', 
		'background-image':'url(../images/skin/body_bg.png)'
	)

	'#header #banner' (
		'width':'100%',
		'height':'110px',
		'background-image':'url(../images/skin/header_bg.png)'
	)

	'#header #banner #banner_cont' (
		'clear':'both',
		'width':'945px',
		'height':'91px',
		'padding-top':'20px',
		'margin-left':'auto',
		'margin-right':'auto'
	)
	
	'#header #banner #banner_cont h1' (
		'float':'left',
		'display': 'block', 
		'width':'201px', 
		'height':'81px', 
		'background':'transparent url(../images/skin/seekin.png) no-repeat 0 0', 
		'text-indent':'-9999px'
	)

	'#header #banner #banner_cont #langage_selection' (
		'float':'right',
		'height':'30px'
	)

	
	'#menu_cont #search_bar' (
		'float':'right',
		'width':'160px'
	)

	'#menu_cont #search_bar #search_field' (
		'width':'120px',
		'height':'18px',
		'margin-top':'6px',
		'padding-top':'4px',
		'padding-left':'30px',
		'padding-right':'10px',
		'border':'0px none #fff',
		'color':'#ccc',
		'background':'transparent url(../images/skin/field_bg.png) no-repeat left'
	)

	'#menu_cont #search_bar #search_field:focus' (
		'color':selectedColor,
		'background-position':'right'
	)
	
	'#menu .navigation li' (
		'height':'40px',
		'border':'0px none #000',
		'background-color': 'transparent'
	)

	'#menu .navigation li a' (
		'display':'inline-block',
		'padding':'0',
		'height':'36px',
		'line-height':'36px',
		'padding-left':'10px',
		'padding-right':'10px',
		'background-color': 'transparent',
		'color':'#000',
		'font-size':'1.5em'
	)

	'#menu .navigation li.navigation_active' (
		'background-image':'url(../images/skin/bar_selected_bg.png)'
	)

	'#menu .navigation li.navigation_active a' ( 
		'border-left':'1px solid #000',
		'border-right':'1px solid #000',
		'color':selectedColor
	)

	'#menu .navigation li.navigation_active a:hover' ( 

	)

	'#menu .navigation li a:hover' (
		'color':selectedColor
	)

	'#menu .navigation li.navigation_active' (
		'border-bottom':'0px none #fff',
		'margin-top':'0px'
	)

	'#menu .navigation li' (

	)

	'#menu .navigation ul' (
		'list-style-position':'outside'
		)
	
	'#menu .navigation' (
		'float':'left',
		'margin':'0px',
		'margin-left':'-1px',
		'padding':'0px',
		'width':'786px'
	)

	'#menu' (
		'margin-top':'2px',
		'height':'40px',
		'background-image':'url(../images/skin/bar_bg.png)'
	)

	'#menu_cont' (
		'width':'945px',
		'margin-left':'auto',
		'margin-right':'auto',
	)

	'#bar' (
		'position':'fixed',
		'width':'100%',
		'height':'36px',
		'left': '0px',
		'bottom': '-1px',
		'z-index':'100000'
	)

	'#bar #bar_cont' (
		'clear':'both',
		'width': '945px',
		'margin-left':'auto',
		'margin-right':'auto',
		'height': '36px',
		'background-image':'url(../images/skin/bar_bg.png)',
		'border':'1px solid #8c8c8c',	
	)

	'#bar #bar_cont .navigation' (
		'margin':'-1px',
		'margin-left':'-1px',
		'padding':'0px',
		'max-width':'50%',
		'clear':'none'
	)


	'#bar #bar_cont .navigation li' (
		'height':'37px',
		'border':'0px none #000',
		'background-color': 'transparent'
	)

	'#bar #bar_cont #bar_left' (
		'float':'left'
	)

	'#bar #bar_cont #bar_right' (
		'float':'right'
	)

	'#bar #bar_cont .navigation li a' (
		'margin-top':'8px',
		'display':'inline-block',
		'padding':'0',
		'height':'22px',
		'line-height':'22px',
		'padding-left':'10px',
		'padding-right':'10px',
		'background-color': 'transparent',
		'color':'#000',
		'font-size':'1.3em'
	)

	'#bar #bar_cont #bar_left li a' (
		'border-right':'1px solid #8c8c8c'
	)

	'#bar #bar_cont #bar_right li a' (
		'border-left':'1px solid #8c8c8c'
	)

	'#bar #bar_cont .navigation li.navigation_active' (
		'background-color':'transparent'
	)

	'#bar #bar_cont .navigation li.navigation_active a' (
		'background-color':'transparent',
		'color':selectedColor
	)

	'#bar #bar_cont .navigation li a:hover' (
		'color':selectedColor
	)

	'#main' ( 
		'width':'945px',
		'padding-top':'60px',
		'margin-left':'auto',
		'margin-right':'auto',
		'clear':'both',
	)
	
	'.flash_message' (
		'color':'red',
		'min-height':'20px',
		'text-align':'center',
		'width':'100%'
	)	

	'h2' (
		'display':'block',
		'width':'100%',
		'text-align':'center',
		'font-size':'1.4em',
		'color':selectedColor
	)
	
	'.boxed_form label' (
		'width':'44%',
		'margin-left':'5%',
		'padding-right':'5%',
		'height':'20px',
		'line-height':'20px',
		'float':'left',
		'text-align':'right',
		'background-image':'url(../images/skin/gradient.png)',
		'background-position': 'bottom right',
		'background-repeat':'repeat-y'
	)

        '.yui-skin-sam .yui-ac' (
                'margin':'0px',
                'padding':'0px',
                'clear':'none',
                'width':'38%',
		'margin-right':'5%',
		'height':'16px',
		'float':'right',
        )
	
	'.boxed_form input[type=\'text\'], .boxed_form input[type=\'password\'], .boxed_form input[type=\'checkbox\'], .boxed_form select, .boxed_form textarea' (
		'width':'38%',
		'margin-right':'5%',
		'height':'16px',
		'float':'right',
		'border':'1px solid #808080'
	)

	'.boxed_form img' (
                'float':'right',
                'margin-top':'10px',
                'margin-bottom':'10px',
                'margin-right':'5%',
                'width':'200px',
		'height':'50px'
	)

        ' .boxed_form select' (
                'height':'20px'
        )

	'.boxed_form input[type=\'checkbox\']' (
		'border':'0px none #000'
	)

	'.boxed_form p[class=\'submit\']' (
		'text-align':'center'
	)
	
	'.boxed_form p, .boxed_form div' (
		'clear':'both',
		'min-height':'20px',
		'padding':'4px'
	)

	'.centered_para' (
		'text-align':'center'
	)
	
	'#login_form' (
		'width':'28%',
		'float':'right',
	)
	
	'#register_form' (
		'float':'left',
		'width':'70%',
	)

    	'#profile_form' (
		'float':'left',
		'width':'70%',
	)

	'#captcha_form' (
		'float':'right',
		'width':'28%',
	)

	'.yui-skin-sam' (
		'margin-top':'10px',
		'margin-bottom':'10px'
	)
	
	'.yui-skin-sam .yui-panel' (
		'margin-top':'-48px',
		'margin-bottom':'50px'
	)

        '.yui-skin-sam .yui-cms-accordion .yui-cms-item.selected .bd' (
                'overflow':'visible'
        )

        '.yui-skin-sam .yui-ac input' (
                'width':'100%'
        )

        '.yui-skin-sam .yui-ac-container' (
                'min-height':'1px'
        )

        '.yui-skin-sam .yui-ac-content' (
               	'position':'relative',
                'width':'90%',
                'margin-top':'-5px'
        )


}