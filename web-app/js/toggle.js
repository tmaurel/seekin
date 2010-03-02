var formationField = function(button, element) {

    var closed = {
      width: { to: 0 }
    };

    var opened = {
      width: { to: 200 }
    };

    var close = new YAHOO.util.Anim(element, closed, 0.2, YAHOO.util.Easing.easeOut);
    var open = new YAHOO.util.Anim(element, opened, 0.2, YAHOO.util.Easing.easeOut);

    YAHOO.util.Event.on(button, 'click', function() {
        var width = YAHOO.util.Dom.getStyle(element, 'width');
        var value = YAHOO.util.Dom.get(button)

        if(value.checked)
        {
            open.animate();
            YAHOO.util.Dom.setStyle(element, 'visibility', 'visible');
        }
        else
        {
            close.animate();
            YAHOO.util.Dom.setStyle(element, 'visibility', 'hidden');
        }
    });

};
