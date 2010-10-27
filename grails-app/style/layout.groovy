selectedColor = '#22325c'
secondColor = '#d59032'
thirdColor = '#121d3a'

style = {
	
	'html *' (
		'margin': 0
	)
	
	'body' (
		'background':'#DADADA url(../images/skin/bg.png) repeat-y center',
		'height':'100%',
                '-x-system-font':'none',
                'font-family':'arial,helvetica,clean,sans-serif',
                'font-size':'13px',
                'font-size-adjust':'none',
                'font-stretch':'normal',
                'font-style':'normal',
                'font-variant':'normal',
                'font-weight':'normal',
                'line-height':'1.231',

	)

	'a img' (
		'border':'0px none #000'
	)

	'a' (
		'color':selectedColor,
	)

	'a:hover' (
		'color':secondColor,
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
		'width':'931px',
		'height':'90px',
		'padding-top':'20px',
		'margin-left':'auto',
		'margin-right':'auto',
                'padding-left':'85px',
                'padding-right':'85px',
                'background':'url(../images/skin/bg_header.png) repeat-y center'

	)

	'#header #banner #banner_cont h1' (
		'float':'left',
		'display': 'block', 
		'width':'220px',
		'height':'83px',
		'background':'transparent url(../images/skin/logo.png) no-repeat 0 0',
		'text-indent':'-9999px'
	)

	'#header #banner #banner_cont #langage_selection' (
		'float':'right',
		'height':'10px',
                'width':'36px'
	)

	'#header #banner #banner_cont #logout_block' (
                'margin-top':'30px',
		'float':'right',
                'clear':'right'
	)

        '#header #banner ul' (
                'list-style-image': 'url(../images/icons/dot.png)'
        )

        '#header #banner li' (
                'float':'left',
                'margin-left':'20px'
        )

        '#header #banner, #header #banner a' (
                'color':'#fff',
                'font-size':'11px',
        )


	
	'#menu_cont #search_bar' (
		'float':'right',
		'width':'35px',
	)

        '#menu_cont #search_bar a' (
                'display':'block',
                'float':'left',
                'position':'relative',
                'height':'18px',
                'padding-top':'11px',
                'padding-bottom':'11px',
                'padding-left':'10px',
                'padding-right':'10px',
                'text-align':'center',
                'width':'20px',
        )

        '#menu_cont #search_bar .message_opened' (
                'background-image':'url(../images/skin/bar_selected_bg.png)'
        )

        '#menu_cont #search_bar #message_link' (
                'width':'20px',
                'height':'30px',
                'background-image':'url(../images/icons/message.png)',
                'background-repeat':'no-repeat',
        )

        '#menu_cont #search_bar #message_link #message_link_unread' (
                'width':'11px',
                'height':'14px',
                'background-image':'url(../images/icons/unread.png)',
                'background-repeat':'no-repeat',
                'text-align':'center',
                'font-size':'11px',
                'color':'#fff',
                'float':'right',
                'padding-right':'2px',
                'padding-top':'1px',
                'margin-top':'7px'
        )

        '#menu_cont #search_bar a' (
                'text-decoration':'none',
                'color':'#fff'
        )

        '#menu_cont #search_bar a:hover' (
                'text-decoration':'none',
                'color':'#fff'
        )

        '#content #message_popup' (
                'position':'relative',
                'left':'0px',
                'width':'923px',
                'height':'340px',
                'border':'1px solid #000',
                'border-top':'none',
                'z-index':'10',
                'margin-top':'-10px',
                'background-color':'#fff',
                'display':'none',
                'padding-left':'20px',
                'padding-top':'1px'
        )

        '.message_unread' (
                'display':'block',
                'width':'20px',
                'height':'18px',
                'background-image':'url(../images/icons/message.png)',
                'background-position':'right',
                'margin-left':'10px'
        )

        '.message_read' (
                'display':'block',
                'width':'20px',
                'height':'18px',
                'background-image':'url(../images/icons/message.png)',
                'background-position':'top center',
                'margin-left':'10px'
        )

        '#message_popup #messageTabView' (
                'height':'270px'
        )

        '#message_popup #messageTabView .yui-content' (
                'height':'230px'
        )

        '.quoted_message' (
                'border':'solid 1px ' + secondColor,
                'background-color': '#ffecd3',
                'padding':'5px',
                'margin-left':'50px',
                'margin-right':'50px',
                'margin-bottom':'30px'
        )

        '#menu_cont #search_bar form' (
                'margin-top':'4px',
                'float':'right'
        )

	'#menu_cont #search_bar #q' (
		'width':'120px',
		'height':'18px',
		'margin-top':'6px',
		'padding-left':'30px',
		'padding-right':'10px',
		'border':'0px none #fff',
		'color':'#000',
		'background':'transparent url(../images/skin/field_bg.png) no-repeat left'
	)

	'#menu_cont #search_bar #q:focus' (
		'color':selectedColor,
		'background-position':'right'
	)


	'#menu .navigation' (
		'float':'left',
		'padding':'0',
		'margin':'0 0 0 -3px',
                'clear':'both',
                'font-size':'12px',
                'list-style-type':'none',
                'overflow':'hidden'
	)

	'#menu .navigation ul' (
		'list-style-position':'outside',
        )

	'#menu .navigation li' (
                'float':'left',
		'border':'0px none #000',
		'background-color': 'transparent',
                'height':'40px'
	)

	'#menu .navigation li a' (
		'display':'inline-block',
		'padding':'0',
		'height':'40px',
		'line-height':'40px',
		'padding-left':'10px',
		'padding-right':'10px',
		'background-color': 'transparent',
		'color':'white',
		'font-size':'1.5em',
                'text-decoration':'none'
	)

	'#menu .navigation li a:hover' (
		'color':secondColor,
	)


	'#menu .navigation li.navigation_active' (
		'background-image':'url(../images/skin/bar_selected_bg.png)'
	)

	'#menu .navigation li.navigation_active a' ( 
		'color':selectedColor,
                'cursor':'default'

	)

	'#menu .navigation li.navigation_active a:hover' ( 
		'color':selectedColor
	)

	'#menu .navigation li.navigation_active' (
		'border-bottom':'0px none #fff',
		'margin-top':'0px'
	)

	'#menu' (
		'height':'40px',
		// 'background-image':'url(../images/skin/bar_bg.png)'
	)

	'#menu_cont' (
		'width':'958px',
                'height':'100%',
                'padding-left':'58px',
                'padding-right':'85px',
                'background':'url(../images/skin/bg_header.png) repeat-y center',
		'margin-left':'auto',
		'margin-right':'auto',
	)

        '#bar_powered' (
                'float':'right',
                'position':'fixed',
                'margin-top':'3px',
                'width':'90px',
                'height': '36px',
                'right': '0px',
		'bottom': '-1px',
		'z-index':'100000'
        )

	'#main' ( 
		'width':'946px',
		'padding-top':'10px',
		'margin-left':'auto',
		'margin-right':'auto',
		'clear':'both',
	)

        '.flash_message' (
                'color':'red'
        )

        '.flash_message ul' (
                'list-style-type':'none'
        )

        '.fielderror' (
                'background-color':'#df6565',
                'color':'white'
        )

        '#submenu' (
            'width':'200px',
            'float':'left',
            'margin-top':'30px',
        )

        '#submenu img' (
            'float':'left',
            'margin-right':'10px',
            'margin-top':'-2px'
        )

        '#submenu a' (
            'text-decoration':'none',
        )

        '#submenu a:hover' (
            'text-decoration':'none',
        )

        '#submenu .yui-panel .bd' (
            'background-image':'url(../images/skin/menu_bg.png)',
        )

        '#submenu li' (
            'height':'17px',
            'padding':'4px',
            'list-style':'none'
        )

        '#submenu ul' (
            'padding':'0px',
            'margin':'0px',
            'margin-top':'-5px',
            'margin-bottom':'-7px'
        )

        '#content' (
            'width':'725px',
            'margin-right':'20px',
            'float':'left',
        )

	'h2' (
		'display':'block',
		'width':'100%',
		'text-align':'left',
		'font-size':'1.4em',
		'color':selectedColor,
		'font': 'bold small-caps 2em Verdana, Arial, Helvetica, sans-serif',
		'letter-spacing':'-1px',
		'padding-top':'0',
		'border-bottom':'2px solid' + selectedColor
	)

	'h3' (
		'font-size':'1.4em',
		'margin':'10px 0 10px 0',
		'text-align':'left',
		'color':secondColor
	)

	'h4' (
		'font-size':'1.2em',
		'margin':'10px 0 10px 20px',
		'text-align':'left',
		'color':selectedColor
	)

        '.field_full_value' (
                'width':'90%',
                'margin-left':'4px',
		'min-height':'18px',
                'padding':'20px',
		'margin-top':'30px',
                'margin-bottom':'10px',
		'border':'solid 1px ' + secondColor,
        )

	'.boxed_form label' (
        'min-width':'100px',
        'margin-right':'10px',
		'height':'20px',
		'line-height':'20px',
		'float':'left',
		'color' : selectedColor,
		'text-align' : 'right',
		'padding-right' : '7px',
		'width' : '200px',
		'text-transform' : 'uppercase',
		'font-family' : 'verdana',
		'border-right': 'solid 3px ' + secondColor,
		'background-color': '#F3F3F3',
		//'background-image':'url(../images/skin/header_bg.png)',
		//'background-position': 'bottom right',
		//'background-repeat':'repeat-y'
	)

	'.boxed_form input[type=\'text\'], .boxed_form input[type=\'password\'], .boxed_form textarea' (
		'width':'55%',
		'height':'17px',
		'border':'1px solid #CCC'
	)

        ' .boxed_form select' (
                'min-height':'20px',
                'margin':'1px',
                'max-width':'42%',
		'border':'1px solid #808080'
        )

	'.boxed_form input[type=\'checkbox\']' (
                'height':'20px',
		'border':'0px none #000'
	)

        'input[type=\'checkbox\']' (
                'margin-right':'5px'
        )

	'.boxed_form .submit' (
		'text-align':'center'
	)

        '.boxed_form .actionpad' (
                'text-align':'left'
        )
	
	'.boxed_form p, .boxed_form div' (
		'clear':'both',
		'padding':'4px'
	)

    '.boxed_form .textarea_field' (
            'clear':'none'
    )

        '.boxed_form .properties_list ul, .textarea_field span' (
		'width':'50%',
		'float':'left',
                'text-align':'left',
                'list-style-type':'none',
                'padding':'0px',
                'margin-bottom':'10px'
        )

        '.boxed_form .properties_list li' (
		'height':'20px'
        )

        '.boxed_form textarea' (
                'height':'150px'
        )

	'.centered_para' (
		'text-align':'center'
	)

    '.field_value' (
 		'width':'50%',
		'min-height':'18px',
        'padding-left':'4px',
		'margin-top':'2px',
		'border-bottom':'solid 1px #CCCCCC',
        )
	
	'#login_form' (
		'width':'38%',
		'float':'right',
	)

	'#login_form label' (
		'min-width':'0',
		'width':'110px',
	)

	'#register_form, #profile_form' (
		'float':'left',
		'width':'60%',
	)

	'#captcha_form' (
		'float':'right',
		'width':'38%',
	)

	'#captcha_form img' (
		'float':'right',
		'margin-right':'10px',
	)

	'#captcha_form label' (
		'min-width':'0',
		'width':'60px',
		'margin-top':'14px',
	)

	'#captcha_form input' (
		'margin-top':'14px',
		'width':'80px',
	)




        '#home_panel' (
                'width':'700px',
                'min-height':'400px',
                'clear':'both',
        )

        '.home_block' (
                'float':'left',
                'width':'48%',
                'padding-right':'2%'
        )

	'.yui-skin-sam' (
		'margin-top':'10px',
		'margin-bottom':'10px'
	)
	
	'.yui-skin-sam .yui-panel' (
		'margin-top':'0px',
		'margin-bottom':'0px',
        'border':'1px solid #AAA'
	)
        '.yui-skin-sam .yui-panel .hd, .yui-skin-sam .yui-panel .bd, .yui-skin-sam .yui-panel .ft' (
                'border-color': '#AAA',
        )

        '.yui-skin-sam .yui-cms-accordion .yui-cms-item.selected .bd' (
                'overflow':'visible'
        )

        '.yui-skin-sam .yui-ac' (
                'margin':'0px',
                'padding':'0px',
                'clear':'none',
                'width':'50%',
		'margin-right':'5%',
		'height':'16px',
		'float':'left',
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

        '.yui-navset' (
                'margin-top':'20px',
                'width':'95%',
        )

		'.yui-skin-sam .yui-navset .yui-nav' (
                'border-bottom':'solid 1px ' + selectedColor,
        )

		'.yui-skin-sam .yui-navset .yui-nav .selected a,.yui-skin-sam .yui-navset .yui-nav .selected a:hover' (
				'background':selectedColor
		)

        '.yui-skin-sam .yui-navset .yui-content' (
                'background':'transparent',
                'border' : 'none',
				//'border':'solid 1px #CCC',
                'padding':'10px',
        )

        '.yui-dt' (

        )

		'.yui-skin-sam .yui-dt-liner' (
			'white-space':'normal',
		)

        '#crud_panel' (
            'padding':'10px',
            'width': '100%'
        )

        '#create a' (
            'background':'url(../images/icons/plus.png) 5% 30% no-repeat',
            'padding-left':'2.25em'
        )

        '#list a' (
            'background':'url(../images/icons/back.png) 5% 40% no-repeat',
            'padding-left':'2.25em'
        )

        '#save button, #reply button' (
            'background':'url(../images/icons/save.png) 10% 40% no-repeat',
            'padding-left':'2.25em'
        )

        '#edit button' (
            'background':'url(../images/icons/edit.png) 10% 40% no-repeat',
            'padding-left':'2.25em'
        )

        '#delete button, #messageDelete button' (
            'background':'url(../images/icons/minus.png) 6% 40% no-repeat',
            'padding-left':'2.25em'
        )

        '#update button' (
            'background':'url(../images/icons/refresh.png) 6% 40% no-repeat',
            'padding-left':'2.25em'
        )


		'.humanMsg' (
				'top':'auto',
				'bottom':'50px',
				'background-color':selectedColor
		)
}