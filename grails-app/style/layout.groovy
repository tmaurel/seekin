selectedColor = '#163281'

style = {
	
	'html *' (
		'margin': 0
	)
	
	'body' (
		'background-color':'#fff',
		'height':'100%',
                '-x-system-font':'none',
                'font-family':'arial,helvetica,clean,sans-serif',
                'font-size':'13px',
                'font-size-adjust':'none',
                'font-stretch':'normal',
                'font-style':'normal',
                'font-variant':'normal',
                'font-weight':'normal',
                'line-height':'1.231'
	)

	'a img' (
		'border':'0px none #000'
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
		'width':'160px'
	)

	'#menu_cont #search_bar #q' (
		'width':'120px',
		'height':'18px',
		'margin-top':'6px',
		'padding-top':'4px',
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
		'width': '200px',
                'float':'left',
		'height': '36px',
		'background-image':'url(../images/skin/bar_bg.png)',
		'border':'1px solid #8c8c8c',	
	)

        '#bar #bar_powered' (
                'float':'right',
                'margin-top':'3px',
                'width':'90px',
                'height': '36px',
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
		'width':'100%',
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
            'margin-top':'50px',
        )

        '#content' (
            'width':'695px',
            'margin-right':'50px',
            'float':'left'
        )

	'h2' (
		'display':'block',
		'width':'100%',
		'text-align':'center',
		'font-size':'1.4em',
		'color':selectedColor
	)
	
	'.boxed_form label' (
		'width':'30%',
                'min-width':'90px',
		'margin-left':'5%',
		'padding-right':'5%',
                'margin-right':'5px',
		'height':'20px',
		'line-height':'20px',
		'float':'left',
		'text-align':'right',
		'background-image':'url(../images/skin/gradient.png)',
		'background-position': 'bottom right',
		'background-repeat':'repeat-y'
	)

	'.boxed_form input[type=\'text\'], .boxed_form input[type=\'password\'], .boxed_form input[type=\'checkbox\'], .boxed_form textarea' (
		'width':'50%',		
		'height':'16px',
		'float':'left',
		'border':'1px solid #808080'
	)


        ' .boxed_form select' (
                'height':'20px',
                'margin':'1px',
                'max-width':'42%',
		'float':'left',
		'border':'1px solid #808080'
        )

	'.boxed_form input[type=\'checkbox\']' (
		'border':'0px none #000'
	)

        'input[type=\'checkbox\']' (
                'margin-right':'5px'
        )

	'.boxed_form .submit' (
		'text-align':'center'
	)
	
	'.boxed_form p, .boxed_form div' (
		'clear':'both',
		'min-height':'20px',
		'padding':'4px'
	)

        '.boxed_form .properties_list ul' (
		'width':'30%',
		'float':'left',
                'text-align':'left',
                'list-style-type':'none',
                'margin-bottom':'10px'
        )

        '.boxed_form textarea' (
                'height':'150px'
        )

	'.centered_para' (
		'text-align':'center'
	)

        '.field_value' (
 		'width':'50%',
		'height':'18px',
                'padding-left':'4px',
		'float':'left',
		'border':'1px solid #808080'
        )
	
	'#login_form' (
		'width':'36%',
		'float':'right',
	)
	
	'#register_form, #profile_form' (
		'float':'left',
		'width':'60%',
	)

	'#captcha_form' (
		'float':'right',
		'width':'36%',
	)

        '#offers_block' (
                'float':'left',
                'width':'36%'
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

        '#crud_panel' (
            'margin-top':'20px',
            'padding':'10px',
            'margin-left':'auto',
            'margin-right':'auto',
            'width': '600px'
        )

        '#create a' (
            'background':'url(../images/icons/plus.png) 5% 30% no-repeat',
            'padding-left':'2.25em'
        )

        '#list a' (
            'background':'url(../images/icons/back.png) 5% 40% no-repeat',
            'padding-left':'2.25em'
        )

        '#save button' (
            'background':'url(../images/icons/save.png) 10% 40% no-repeat',
            'padding-left':'2.25em'
        )

        '#edit button' (
            'background':'url(../images/icons/edit.png) 10% 40% no-repeat',
            'padding-left':'2.25em'
        )

        '#delete button' (
            'background':'url(../images/icons/minus.png) 6% 40% no-repeat',
            'padding-left':'2.25em'
        )

        '#update button' (
            'background':'url(../images/icons/refresh.png) 6% 40% no-repeat',
            'padding-left':'2.25em'
        )

		'#search .title' (
			'margin': '1em 0',
			'padding': '.3em .5em',
			'text-align': 'right',
			'background-color': '#eee',
			'border-top': '1px solid darkblue'
		)

		'#search .result' (
			'margin-bottom': '1em'
		)

		'#search .result a' (
			'color': 'darkblue'
		)

		'#search .result .displayLink' (
			'color': 'green'
		)

		'#search .result .name' (
			'font-size': 'larger'
		)

		'#search .paging a.step' (
			'padding': '0 .3em'
		)

		'#search .paging span.currentStep' (
			'font-weight': 'bold'
		)

		'#search ul' (
			'margin': '1em 2em'
		)

		'#search li, #search p' (
			'margin-bottom': '1em'
		)

		'#search .class_type' (
			'font-size': 'x-small',
			'color': 'darkblue',
			'text-transform': 'uppercase'
		)
}