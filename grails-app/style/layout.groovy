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
		'border':'0px none #000',
		'background-color': 'transparent'
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
		'font-size':'1.5em'
	)

	'#menu .navigation li.navigation_active' (
		'background-image':'url(../images/skin/bar_selected_bg.png)'
	)

	'#menu .navigation li.navigation_active a' ( 
		'color':selectedColor
	)

	'#menu .navigation li.navigation_active a:hover' ( 
		'color':selectedColor
	)

	'#menu .navigation li a:hover' (
		'color':secondColor,
	)

	'#menu .navigation li.navigation_active' (
		'border-bottom':'0px none #fff',
		'margin-top':'0px'
	)

	'#menu .navigation li' (
		'height':'40px'
	)

	'#menu .navigation ul' (
		'list-style-position':'outside',
		)
	
	'#menu .navigation' (
		'float':'left',
		'padding':'0',
		'width':'772px',
		'margin':'0 0 0 -3px',
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
		'float':'left',
		'border':'1px solid #CCC'
	)

        ' .boxed_form select' (
                'min-height':'20px',
                'margin':'1px',
                'max-width':'42%',
		'float':'left',
		'border':'1px solid #808080'
        )

	'.boxed_form input[type=\'checkbox\']' (
                'float':'left',
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

        '.boxed_form .properties_list ul' (
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
		'float':'left',
		'margin-top':'2px',
		'border-bottom':'solid 1px ' + secondColor,
        )
	
	'#login_form' (
		'width':'36%',
		'float':'right',
	)

	'#login_form label' (
		'width':'10px',
		'border':'none',
	)
	
	'#register_form, #profile_form' (
		'float':'left',
		'width':'60%',
	)

	'#register_form label' (
		'border':'none',
	)

	'#captcha_form' (
		'float':'right',
		'width':'36%',
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
                'height':'400px',
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

                '.humanMsg' (
                        'top':'auto',
                        'bottom':'50px',
                        'background-color':selectedColor
                )
}