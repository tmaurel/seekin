/* 
 * Add a custom formatter for our datatables to display a link to the related object
 */

var customLinkFormatter = function(elLiner, oRecord, oColumn, oData) {
  var name = oData.name;
  var link = oData.link;
  if(typeof(name) != "object")
    elLiner.innerHTML = "<a href=\"" + link + "\">" + name + "</a>";
  else
    elLiner.innerHTML = "";
};

var adminPanelFormatter = function(elLiner, oRecord, oColumn, oData) {
  var id = oData;
  elLiner.innerHTML = "<a href=\"show/" + id + "\"><img src=\"../images/icons/show.png\" /></a>\n\
                        <a href=\"edit/" + id + "\"><img src=\"../images/icons/edit.png\" /></a>";
};

YAHOO.widget.DataTable.Formatter.customLinkFormatter = customLinkFormatter;
YAHOO.widget.DataTable.Formatter.adminPanelFormatter = adminPanelFormatter;