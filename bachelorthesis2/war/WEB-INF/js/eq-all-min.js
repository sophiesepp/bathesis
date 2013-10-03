(function(d,c){var b=c.EQ=c.EQ||{};var a={Locale:"en",AltMenuAttribute:"Attribute",AltMenuConstantExpression:"Constant expression",ButtonApply:"Apply",ButtonCancel:"Cancel",ButtonOK:"OK",ButtonEnable:"Toggle enable",ButtonDelete:"Delete",ButtonAddCondition:"Add condition",ButtonAddPredicate:"Add predicate",CmdAddConditionAfter:"Add a new condition after the current row",CmdAddConditionInto:"Add a new condition",CmdAddPredicateAfter:"Open a bracket after the current row",CmdAddPredicateInto:"Open a bracket",CmdClickToAddCondition:"[Add new condition]",CmdDeleteRow:"Delete this row",ErrIncorrectPredicateTitleFormat:"Incorrect predicate title format",False:"False",FirstDayOfMonth:"First day of the month",FirstDayOfYear:"First day of the year",HourStart:"This hour start",LinkTypeAll:"all",LinkTypeAny:"any",LinkTypeNone:"none",LinkTypeNotAll:"not all",Midnight:"Midnight",MsgApplySelection:"[Apply selection]",MsgAs:"as",MsgEmptyList:"(empty list)",MsgEmptyListValue:"[select value]",MsgEmptyScalarValue:"[enter value]",MsgSubQueryValue:"[edit sub-query]",MsgOf:"of",Noon:"Noon",Now:"Now",PredicateTitle:"{lt} of the following apply",RootPredicateTitle:"Select records where {lt} of the following apply",StrAddConditions:"Add conditions",SubQueryDialogTitle:"Edit sub-query",Today:"Today",Tomorrow:"Tomorrow",True:"True",Yesterday:"Yesterday",CmdAscending:"Ascending",CmdClickToAddColumn:"[Add new column]",CmdDeleteColumn:"Delete column",CmdDeleteSorting:"Delete sorting",CmdDescending:"Descending",CmdGroupSort:"Sorting",CmdNotSorted:"Not sorted",ColTypeAggrFunc:"Aggregate function",ColTypeCompound:"Calculated",ColTypeGroup:"Column type",ColTypeSimple:"Simple column",HeaderExpression:"Expression",HeaderSorting:"Sorting",HeaderTitle:"Title",SortHeaderColumn:"Column",SortHeaderSorting:"Sorting",StrAddColumns:"Add columns"};b.core={texts:{},constLists:{SpecDateValues:[{id:"${Today}",key:"Today"},{id:"${Yesterday}",key:"Yesterday"},{id:"${Tomorrow}",key:"Tomorrow"},{id:"${FirstDayOfMonth}",key:"FirstDayOfMonth"},{id:"${FirstDayOfYear}",key:"FirstDayOfYear"}],SpecTimeValues:[{id:"${Now}",key:"Now"},{id:"${HourStart}",key:"HourStart"},{id:"${Midnight}",key:"Midnight"},{id:"${Noon}",key:"Noon"}],BooleanValues:[{id:"${false}",key:"False"},{id:"${true}",key:"True"}]},predicateLinkTypeList:[{id:"All",key:"LinkTypeAll"},{id:"Any",key:"LinkTypeAny"},{id:"None",key:"LinkTypeNone"},{id:"NotAll",key:"LinkTypeNotAll"}],getText:function(){var e=b.core.texts;var f=false;for(i=0;i<arguments.length;i++){e=e[arguments[i]];if(!e){f=true;break}}if(f){e=a;for(i=0;i<arguments.length;i++){e=e[arguments[i]];if(!e){break}}}return e},listByEntity:function(f,e,h,g){var n=[];var m;var k=null;for(attrIdx in f.attributes){k=f.attributes[attrIdx];if(e!==false&&k.UIC!==false||h!==false&&k.UIR!==false||g!==false&&k.UIS!==false){m=b.core.getText("Attributes",k.id);if(!m){m=k.caption}n.push({id:k.id,text:m})}}var l=null;for(entIdx in f.subEntities){l=f.subEntities[entIdx];if(e!==false&&l.UIC!==false||h!==false&&l.UIR!==false||g!==false&&l.UIS!==false){m=b.core.getText("Entities",l.name);if(!m){m=l.caption}var j={id:l.name,text:m,items:[]};j.items=b.core.listByEntity(l,e,h,g);n.push(j)}}return n}}})(jQuery,window);(function(a,b){a.widget("eqjs.PopupMenu",{_mouseIsOverBlock:false,_mouseIsOverLink:false,_toId:null,_itemSelectedCallback:null,_menuClosedCallback:null,options:{items:[],buttons:{submit:EQ.core.getText("ButtonApply"),cancel:EQ.core.getText("ButtonCancel")},multiselect:false},_create:function(){var c=this._renderHtmlByList(this.options.items,this.options.multiselect);this.element.html(c);this._render()},_render:function(){var c=this;this.element.addClass("eqjs-menu-block ui-widget-content").andSelf().find("ul li:has(ul)").find("ul").addClass("ui-widget-content");if(c.options.multiselect){c._renderMultiselectMenu();c._renderSubmitButtons()}else{c._renderSimpleMenu()}c.element.mouseenter(function(){c._mouseIsOverBlock=true});c.element.mouseleave(function(){c._mouseIsOverBlock=false;if(c._toId){clearTimeout(c._toId)}c._toId=setTimeout(function(){if(!(c._mouseIsOverLink||c._mouseIsOverBlock)){c.hideMenu()}},200)})},_renderSimpleMenu:function(){var c=this;this.element.find("ul li").hover(function(f){a(this).children("a").addClass("ui-state-hover");a(this).find("ul:first").stop(true,true);a(this).find("ul:first").fadeIn("fast");if(a(f.target).parent().find("ul").length==1){var d=f.screenY+a(f.target).parent().find("ul").offset().top+50;if(d>a(window).height()){}else{}}},function(d){a(this).find("ul:first").fadeOut("fast");a(this).children("a").removeClass("ui-state-hover")});this.element.find("li:has(ul)").find("a:first").append("&nbsp;&nbsp;&raquo;");this.element.find("ul li:not(:has(ul))").click({widget:this},function(d){d.data.widget.element.find("ul ul").hide();d.data.widget.element.hide();if(c._itemSelectedCallback){c._itemSelectedCallback.apply(c,[null,{widget:d.data.widget,menuItem:a(this)}])}else{d.data.widget._trigger("onMenuItemSelected",null,{widget:d.data.widget,menuItem:a(this)})}return false})},_renderMultiselectMenu:function(){var c=this;this.element.find("ul li").hover(function(f){a(this).children("label").addClass("ui-state-hover");a(this).find("ul:first").stop(true,true);a(this).find("ul:first").fadeIn("fast");if(a(f.target).parent().find("ul").length==1){var d=f.screenY+a(f.target).parent().find("ul").offset().top+50}},function(d){a(this).find("ul:first").fadeOut("fast");a(this).children("label").removeClass("ui-state-hover")});this.element.find("li:has(ul)").find("label:first").append("&nbsp;&nbsp;&raquo;");a.each(this.element.find("ul li").has("ul"),function(d,e){a(e).on("click","label input:first-child",function(f){if(!(a(this).parent().siblings("ul").find("input").is(":checked"))){a(this).parent().siblings("ul").find("input").attr("checked",true)}else{a(this).parent().siblings("ul").find("input").removeAttr("checked")}})});this.element.find("ul li:not(:has(ul)) input").click(function(h){var d=a(this).parents("ul").first().find("input");var g=0;for(var f=0;f<d.length;f++){if(a(d[f]).is(":checked")){g++}if(g==d.length){a(this).parents("[class*=korzh-dd-menu-head-block]").first().find("input").first().attr("checked",true)}if(g<d.length&&g>0){a(this).parents("[class*=korzh-dd-menu-head-block]").first().find("input").first().replaceWith('<input class="korzh-dd-menu-state-checkbox" type="none" />')}else{if(a(this).parents("[class*=korzh-dd-menu-head-block]").first().find("ul input").is(":checked")){a(this).parents("[class*=korzh-dd-menu-head-block]").first().find("input").first().replaceWith('<input type="checkbox" checked="checked"  />')}else{a(this).parents("[class*=korzh-dd-menu-head-block]").first().find("input").first().replaceWith('<input type="checkbox" />')}}}a(".korzh-dd-menu-state-checkbox").click(function(){a(this).parent().siblings("ul").find("input").attr("checked",true);a(this).replaceWith('<input type="checkbox" checked="checked" />')})})},_renderHtmlByList:function(d,e){var c=e?"<ul>":"<ul>";for(elem in d){if(("items" in d[elem])){c+=e?'<li data-id="'+d[elem].id+'"><label><input type="checkbox" />'+d[elem].text+"</label>":'<li data-id="'+d[elem].id+'"><a href="javascript:void(0)" title="'+d[elem].text+'">'+d[elem].text+"</a>";c+=getMenuHtmlByList(d[elem].items,e);c+="</li>"}else{c+=e?'<li data-id="'+d[elem].id+'"><label><input type="checkbox" />'+d[elem].text+"</label></li>":'<li data-id="'+d[elem].id+'"><a href="javascript:void(0)" title="'+d[elem].text+'">'+d[elem].text+"</a></li>"}}c+="</ul>";return c},_renderSubmitButtons:function(){var c=this;var e=this.options.buttons;var g=a("<div></div>",{"class":"eqjs-menu-block-header ui-widget-header"}).prependTo(c.element);if(e.submit){var f=a("<button></button>",{"class":"eqjs-menu-block-button ui-button ui-state-default ui-corner-all"}).wrapInner(a("<span></span>",{text:e.submit,"class":"ui-button-text"})).hover(function(){a(this).toggleClass("ui-state-hover")}).click(function(){var h=[];a.each(c.element.find("input"),function(k,l){if(a(l).prop("checked")){var j=a(l).parent().parent().attr("data-id");if(!(j=="undefined")){h.push(j)}}});c.hideMenu();c._trigger("onMenuItemSelected",null,{result:h})}).appendTo(g)}if(e.cancel){var d=a("<button></button>",{"class":"eqjs-menu-block-button ui-button ui-state-default ui-corner-all"}).wrapInner(a("<span></span>",{text:e.cancel,"class":"ui-button-text"})).hover(function(){a(this).toggleClass("ui-state-hover")}).click(function(){c.hideMenu()}).appendTo(g)}},_setOption:function(c,d){if(arguments.length==2){this.options[c]=d;return this}else{return this.options[c]}},showMenu:function(f,e,d){var c=this;c._mouseIsOverLink=false;c._mouseIsOverBlock=false;c._itemSelectedCallback=e;c._menuClosedCallback=d;f.mouseenter(function(){c._mouseIsOverLink=true});f.mouseleave(function(){c._mouseIsOverLink=false;if(c._toId){clearTimeout(c._toId)}c._toId=setTimeout(function(){if(!(c._mouseIsOverBlock||c._mouseIsOverLink)){c.hideMenu()}},300)});c.options.source=f;var g=f.position();c.element.css({left:g.left,top:g.top+f.outerHeight()}).stop(true,true).slideDown("fast")},hideMenu:function(){this.element.find("ul li ul").fadeOut("fast");this.element.fadeOut("fast").queue(false);if(this._menuClosedCallback){this._menuClosedCallback.call()}}})})(jQuery);function getAttributeById(a,b){for(attr in a.attributes){if(a.attributes[attr].id==b){return a.attributes[attr]}}for(ent in a.subEntities){res=getAttributeById(a.subEntities[ent],b);if(res){return res}}return null}function getEntityById(a,b){if(a.name===b){return a}for(ent in a.subEntities){res=getEntityById(a.subEntities[ent],b);if(res){return res}}return null}function getOperatorById(a,b){for(oper in a.operators){if(a.operators[oper].id==b){return a.operators[oper]}}return null}function getFullEntitiesPathByAttribute(a,c,b){for(attr in a.attributes){if(a.attributes[attr].id==c){return"."}}for(ent in a.subEntities){res=getFullEntitiesPathByAttribute(a.subEntities[ent],c,b);if(res){if(res==="."){return a.subEntities[ent].caption}else{return a.subEntities[ent].caption+b+res}}}return undefined}function getMenuHtmlByList(b,c){var a=c?'<ul class="ui-helper-reset">':"<ul>";for(elem in b){if(!("items" in b[elem])){a+=c?'<li data-id="'+b[elem].id+'"><label><input type="checkbox" />'+b[elem].text+"</label></li>":'<li data-id="'+b[elem].id+'"><a href="javascript:void(0)" title="'+b[elem].text+'">'+b[elem].text+"</a></li>"}}for(elem in b){if("items" in b[elem]){a+=c?'<li data-id="'+b[elem].id+'"><label><input type="checkbox" />'+b[elem].text+"</label>":'<li data-id="'+b[elem].id+'"><a href="javascript:void(0)" title="'+b[elem].text+'">'+b[elem].text+"</a>";a+=getMenuHtmlByList(b[elem].items,c);a+="</li>"}}a+="</ul>";return a}function inherit(c){function b(){}b.prototype=c;var a=new b;return a}function getOffset(a){if(a.getBoundingClientRect){return getOffsetRect(a)}else{return getOffsetSum(a)}}function getOffsetSum(a){var c=0,b=0;while(a){c=c+parseInt(a.offsetTop);b=b+parseInt(a.offsetLeft);a=a.offsetParent}return{top:c,left:b}}function getOffsetRect(d){var g=d.getBoundingClientRect();var h=document.body;var b=document.documentElement;var a=window.pageYOffset||b.scrollTop||h.scrollTop;var e=window.pageXOffset||b.scrollLeft||h.scrollLeft;var f=b.clientTop||h.clientTop||0;var j=b.clientLeft||h.clientLeft||0;var k=g.top+a-f;var c=g.left+e-j;return{top:Math.round(k),left:Math.round(c)}}function ElemCoords(b){var c=0;var a=0;if(b.offsetParent){while(1){c+=b.offsetLeft;a+=b.offsetTop;if(!b.offsetParent){break}b=b.offsetParent}}else{if(b.x||b.y){c+=b.x;a+=b.y}}return{x:c,y:a}}(function(a,b){a.widget("eqjs.ConditionRow",{_condition:null,_parentPredicate:null,_parentPredicateWidget:null,_buttonsBlock:null,_enableButton:null,_deleteButton:null,_addConditionButton:null,_addPredicateButton:null,_keepShowingButtons:false,_isMouseOverBlock:false,options:{queryPanel:null,model:null},init:function(e,d,c){this._condition=e;this._parentPredicate=d;this._parentPredicateWidget=c;this.refresh()},_render:function(){this._clear();if(this.options.model&&this._condition){this._refreshByCondition();this._initButtons()}},refresh:function(){this._render()},_setOption:function(c,d){if(arguments.length==2){this.options[c]=d;if(c==="disabled"){this._setConditionEnable(!d)}this._render();return this}else{if(c==="disabled"){return this._condition.enabled===false}else{return this.options[c]}}},_setConditionEnable:function(c){if(this._parentPredicate){if(!c||!("enabled" in this._parentPredicate)||this._parentPredicate.enabled){this._setConditionEnableCore(this._condition,c)}}},_setConditionEnableCore:function(c,d){c.enabled=d;if(c.conditions){for(condIdx in c.conditions){this._setConditionEnableCore(c.conditions[condIdx],d)}}},_clear:function(){this.element.html("");this.element.removeClass()},_refreshByCondition:function(){},remove:function(){var c=this;if(!c._parentPredicateWidget){return}c._parentPredicateWidget.removeCondition(c._condition)},destroy:function(){a.Widget.prototype.destroy.call(this)},_getButtonsContainer:function(){return this.element},_initButtons:function(){var d=this;var c=d._getButtonsContainer();if(!c){return}d._buttonsBlock=a("<div></div>").hide().addClass("eqjs-qp-condition-buttonsBlock").appendTo(c);d._addConditionButton=a("<div></div>").addClass("eqjs-qp-condition-button eqjs-qp-condition-button-addCondition").attr("title",EQ.core.getText("ButtonAddCondition")).appendTo(d._buttonsBlock).click(function(){d._keepShowingButtons=true;d.options.queryPanel.options.entitiesMenu.PopupMenu("showMenu",d._addConditionButton,function(f,j){var h=j.menuItem.data("id");var g=getAttributeById(d.options.model.rootEntity,h);var e=g.operators[0];if("addNewCondition" in d){d.addNewCondition(h,e)}},function(){d._keepShowingButtons=false;if(!d._isMouseOverBlock){d._buttonsBlock.hide()}})});d._addPredicateButton=a("<div></div>").addClass("eqjs-qp-condition-button eqjs-qp-condition-button-addPredicate").attr("title",EQ.core.getText("ButtonAddPredicate")).appendTo(d._buttonsBlock).click(function(){if("addNewPredicate" in d){d.addNewPredicate()}});d._enableButton=a("<div></div>").addClass("eqjs-qp-condition-button eqjs-qp-condition-button-enable").attr("title",EQ.core.getText("ButtonEnable")).appendTo(d._buttonsBlock).click(function(){d.option("disabled",!d.options.disabled);if(d.options.disabled){d._enableButton.removeClass("enabled")}else{d._enableButton.addClass("enabled")}d._enableButton.trigger("mouseover")});d._deleteButton=a("<div></div>").addClass("eqjs-qp-condition-button eqjs-qp-condition-button-delete").attr("title",EQ.core.getText("ButtonDelete")).appendTo(d._buttonsBlock).click(function(){d.remove()});(d.options.disabled)?d._enableButton.removeClass("enabled"):d._enableButton.addClass("enabled");c.find("[class*=eqjs-qp-condition-button]").hover(function(){a(this).addClass("eqjs-qp-condition-button-active")},function(){a(this).removeClass("eqjs-qp-condition-button-active")});c.bind("mouseenter",function(e){d._isMouseOverBlock=true;d._showButtons();e.stopPropagation();return false}).bind("mouseleave",function(e){d._isMouseOverBlock=false;if(!d._keepShowingButtons&&d._buttonsBlock.is(":visible")){d._buttonsBlock.hide()}e.stopPropagation();return false});d._adjustButtons()},_adjustButtons:function(){this._addConditionButton.addClass("eqjs-qp-condition-button-hidden");this._addPredicateButton.addClass("eqjs-qp-condition-button-hidden")},_showButtons:function(){var d=this;if(d._buttonsBlock.is(":visible")){return}var c=d._getButtonsContainer();if(!c){return}d._buttonsBlock.show()}})})(jQuery);(function(a,b){a.widget("eqjs.ConditionRow_SMPL",a.eqjs.ConditionRow,{_displayFormatParser:{formatStr:"",pos:0,exprNum:0,token:"text",tokenText:"",start:function(c){this.formatStr=c;this.pos=0;this.exprNum=0;this.tokenText=""},skipSpaces:function(){while(this.pos<this.formatStr.length&&this.formatStr[this.pos]===" "){this.pos++}},next:function(){this.skipSpaces();if(this.pos>=this.formatStr.length){return false}if(this.formatStr[this.pos]==="{"){var e=this.formatStr.indexOf("}",this.pos);if(e<0){return false}this.tokenText=this.formatStr.substring(this.pos,e+1);if(this.tokenText.indexOf("{expr")===0){this.token="expression";this.exprNum=parseInt(this.tokenText.substring(5,this.tokenText.length))}this.pos=e+1}else{if(this.formatStr[this.pos]==="["&&this.pos<this.formatStr.length-1&&this.formatStr[this.pos+1]==="["){this.pos+=2;var e=this.formatStr.indexOf("]]",this.pos);this.token="operator";this.tokenText=this.formatStr.substring(this.pos,e);this.pos=e+2}else{this.token="text";var d=this.formatStr.indexOf("{",this.pos);if(d<0){d=this.formatStr.length}var c=this.formatStr.indexOf("[[",this.pos);if(c<0){c=this.formatStr.length}var e=Math.min(d,c);this.tokenText=a.trim(this.formatStr.substring(this.pos,e));this.pos=e}}return true}},_refreshByCondition:function(){if(!this._condition||!this.options.model){return}var p=this;this.element.addClass("eqjs-qp-row eqjs-qp-row-condition");if(this._condition.enabled===false){this.element.addClass("eqjs-qp-disabled")}if(this._condition.justAdded){this._updateValueExpressions();this._condition.justAdded=false}var e=getOperatorById(p.options.model,p._condition.operatorID);if(!e.displayFormat){return}var d=p._parseDisplayFormat(e);var o=null;for(var l in d){o=d[l];if(o.type==="operator"){if(this._condition.enabled!==false){var f=a("<a></a>",{href:"javascript:void(0)",text:o.text})}else{var f=a("<span></span>",{text:o.text})}var c=a("<div></div>");c.addClass("eqjs-qp-condelement eqjs-qp-operelement");f.appendTo(c);c.appendTo(this.element);if(this._condition.enabled!==false){var j=p._createOperatorsMenu(f);f.click(function(r){j.PopupMenu("showMenu",f)})}}else{if(o.type==="expression"){if(o.index===0){var q=this._condition.expressions[0].id;var h=getAttributeById(this.options.model.rootEntity,q);if(this._condition.enabled!==false){var k=a('<a href="javascript:void(0)">'+p._getAttributeText(h)+"</a>")}else{var k=a("<span>"+p._getAttributeText(h)+"</span>")}var g=a("<div></div>");if(this._condition.enabled!==false){k.click(function(r){p.options.queryPanel.options.entitiesMenu.PopupMenu("showMenu",k,function(t,w){var v=w.menuItem.data("id");var u=getAttributeById(p.options.model.rootEntity,v);var s=u.operators[0];p._condition.expressions[0].id=v;p._condition.operatorID=s;p._updateValueExpressions();p.refresh();return false})})}g.addClass("eqjs-qp-condelement eqjs-qp-attrelement");k.appendTo(g);g.appendTo(this.element)}else{var n=a("<div></div>");n.appendTo(this.element);this._createValueEditor(n,this._condition.expressions[o.index])}}else{if(o.type==="text"){var m=a("<span></span>").addClass("eqjs-qp-condelement").text(o.text).appendTo(this.element)}}}}},_getOperatorCaption:function(d){var c=EQ.core.getText("Operators",d.id,"caption");if(!c){c=d.caption}return c},_parseDisplayFormat:function(d){var e=EQ.core.getText("Operators",d.id,"displayFormat");if(!e){e=d.displayFormat}var c=[];var f=this._displayFormatParser;f.start(e);while(f.next()){if(f.token==="operator"){c.push({type:"operator",text:f.tokenText})}else{if(f.token==="expression"){c.push({type:"expression",index:f.exprNum-1})}else{if(f.token==="text"){c.push({type:"text",text:f.tokenText})}}}}return c},_getAttributeText:function(f){var d=this;var k=EQ.core.getText("Attributes",f.id);if(!k){k=f.caption}if(!d.options.queryPanel){return k}var h=d.options.queryPanel.options.attrElementFormat;if(!h){return k}var c=h.replace(new RegExp("{attr}","g"),k);if(h.indexOf("{entity}")>=0){var j=getFullEntitiesPathByAttribute(d.options.model.rootEntity,f.id,".");var e=j.split(".");var g;a.each(e,function(l,m){g=EQ.core.getText("Entities",m);if(!g){g=getEntityById(d.options.model.rootEntity,m).caption}j=j.replace(new RegExp(m,"g"),g)});c=c.replace(new RegExp("{entity}","g"),j)}return c},_operatorChanged:function(c,d){this._updateValueExpressions();this.refresh()},_updateValueExpressions:function(){var d=this;d._condition.expressions.splice(1,100);var e=getAttributeById(d.options.model.rootEntity,d._condition.expressions[0].id);if(!e){return}var c=getOperatorById(d.options.model,d._condition.operatorID);if(!c){return}var f=(c.exprType&&c.exprType!=="Unknown")?c.exprType:e.dataType;for(i=1;i<c.paramCount;i++){d._condition.expressions.push(d._createValueExpression(f,c.valueKind))}},_createValueExpression:function(f,e){var c=this;var d;if(e==="Query"){d={typeName:"QUERY",dataType:f,kind:e,value:a.eqjs.QueryPanel.emptyQuery(),text:""}}else{if(e==="Attribute"){}else{d={typeName:"CONST",dataType:f,kind:e,value:"",text:""}}}return d},_createOperatorsMenu:function(j){var g=this;if(!g._condition||!g.options.model){return}var f=[];var c=getAttributeById(g.options.model.rootEntity,g._condition.expressions[0].id);if(c){var e=null;for(opIndex in c.operators){e=getOperatorById(g.options.model,c.operators[opIndex]);if(e&&!(g.options.queryPanel.options.isSubQuery&&e.valueKind==="Query")){var d=g._getOperatorCaption(e);f.push({id:e.id,text:d})}}}var h=a("<div></div>").appendTo(j.parent()).hide();h.PopupMenu({items:f,onMenuItemSelected:function(k,m){j.text(a(m.menuItem).text());var l=g._condition.operatorID;g._condition.operatorID=a(m.menuItem).attr("data-id");g._operatorChanged(l,g._condition.operatorID);return false}});return h},_getDefaultEditor:function(d,g){var e=null;var c=getOperatorById(this.options.model,d);var f=getAttributeById(this.options.model.rootEntity,g);if(c&&c.defaultEditor){e=c.defaultEditor}else{if(f&&f.defaultEditor){e=f.defaultEditor}}return e},_createValueEditor:function(d,g){var f=this._getDefaultEditor(this._condition.operatorID,this._condition.expressions[0].id);var c=f?f.type:"EDIT";if(g&&g.kind==="Query"){c="SUBQUERY"}var e="element.ValueEditor_"+c+"({queryPanel: self.options.queryPanel, model: self.options.model, condition: self._condition, editor: editorDescr}); element.ValueEditor_"+c+"('init', expr);";var h=new Function("self, element, expr, editorDescr",e);h(this,d,g,f)}})})(jQuery);(function(a,b){a.widget("eqjs.ConditionRow_PDCT",a.eqjs.ConditionRow,{_predicateRowBlock:null,_getButtonsContainer:function(){return this._predicateRowBlock},_adjustButtons:function(){},_parsePredicateText:function(c,g){var e=this;var h=EQ.core.getText(g);var k=h.indexOf("{lt}");if(k<0){c.text(EQ.core.getText("ErrIncorrectPredicateTitleFormat"));c.addClass("eqjs-qp-error")}else{if(k>0){var f=a("<span></span>").addClass("eqjs-qp-predelement").text(h.substring(0,k)).appendTo(c)}var j=a("<div></div>");j.PredicateLinkType({queryPanel:e.options.queryPanel});j.PredicateLinkType("init",e._condition);j.appendTo(c);var d=a("<span></span>").addClass("eqjs-qp-predelement").text(h.substring(k+4)).appendTo(c)}},_refreshByCondition:function(){var c=this;c.element.addClass("eqjs-qp-predicate");c._predicateRowBlock=a("<div></div>");c._predicateRowBlock.addClass("eqjs-qp-row eqjs-qp-row-predicate");if(c._condition.enabled===false){c._predicateRowBlock.addClass("eqjs-qp-disabled")}c._predicateRowBlock.appendTo(c.element);c._parsePredicateText(c._predicateRowBlock,"PredicateTitle");c._addConditions(c.element)},_addConditions:function(e){var c=a("<div></div>");c.addClass("eqjs-qp-conditions");c.appendTo(e);for(condIdx in this._condition.conditions){var d=a("<div></div>");d.appendTo(c);var f="element.ConditionRow_"+this._condition.conditions[condIdx].typeName+"({queryPanel: self.options.queryPanel, model: self.options.model}); element.ConditionRow_"+this._condition.conditions[condIdx].typeName+"('init', self._condition.conditions[condIdx], self._condition, self);";var g=new Function("self, element, idx",f);g(this,d,condIdx)}},removeCondition:function(e){var c=this;var d=c._condition.conditions.indexOf(e);if(d>=0){c._condition.conditions.splice(d,1);c.refresh()}},addCondition:function(d){var c=this;c._condition.conditions.push(d);c.refresh()},addNewCondition:function(g,c){var e=this;var h=getAttributeById(e.options.model.rootEntity,g);if(!h){return}var j=c?c:h.operators[0];var d=getOperatorById(e.options.model,j);if(!d){return}var f={justAdded:true,typeName:"SMPL",enabled:true,operatorID:j,expressions:[{typeName:"ENTATTR",id:g}]};e.addCondition(f)},addNewPredicate:function(){var e=this;var d=e._condition.linkType==="All"?"Any":"All";var c={typeName:"PDCT",linkType:d,conditions:[]};this.addCondition(c)}})})(jQuery);(function(a,b){a.widget("eqjs.RootPredicate",a.eqjs.ConditionRow_PDCT,{_adjustButtons:function(){this._enableButton.addClass("eqjs-qp-condition-button-hidden");this._deleteButton.addClass("eqjs-qp-condition-button-hidden")},_refreshByCondition:function(){var c=this;this.element.addClass("eqjs-qp-predicate eqjs-qp-predicate-root");if(this.options.queryPanel&&this.options.queryPanel.options.showRootRow){c._predicateRowBlock=a("<div></div>");c._predicateRowBlock.addClass("eqjs-qp-row eqjs-qp-row-predicate eqjs-qp-row-predicate-root");c._predicateRowBlock.appendTo(this.element);this._parsePredicateText(c._predicateRowBlock,"RootPredicateTitle")}this._addConditions(this.element)}})})(jQuery);(function(a,b){a.widget("eqjs.QueryPanel",{options:{model:null,query:{root:{linkType:"All",enabled:true,conditions:[]},columns:[],justsorted:[]},showRootRow:true,showAddRow:true,dateFormatValue:"mm/dd/yy",dateFormatDisplay:"d MM, yy",attrElementFormat:"{entity}.{attr}"},getSelf:function(){return this},_updateLists:function(){for(var c in EQ.core.constLists){this._updateList(EQ.core.constLists[c])}this._updateList(EQ.core.predicateLinkTypeList)},_updateList:function(d){if(!d){return}for(var c in d){d[c].text=EQ.core.getText(d[c].key);if(!d[c].text){d[c].text=d[c].key}}},_create:function(){this._render();this.element.resize(function(){this._render()})},_render:function(){var e=this;this._clear();this._updateLists();if(this.options.model!=null){this.options.entitiesList=EQ.core.listByEntity(this.options.model.rootEntity,true,false,false);this.options.entitiesMenu=this._createEntitiesMenu();this._refreshByQuery();if(this.options.showAddRow){var d=a('<div class="eqjs-qp-addrow"></div>');d.appendTo(this.element);var g=a('<a href="javascript:void(0)">'+EQ.core.getText("CmdClickToAddCondition")+"</a>");g.appendTo(d);g.click(function(h){e.options.entitiesMenu.PopupMenu("showMenu",g,function(k,n){var m=n.menuItem.data("id");var l=getAttributeById(e.options.model.rootEntity,m);var j=l.operators[0];e.addNewCondition(m,j);return false})})}}e.element.css({position:"relative"});var f=a("<div></div>").appendTo(e.element).css({position:"absolute",bottom:"0px",right:"0px"});var c=a("<a></a>").text("Powered by EasyQuery").attr("href","http://devtools.korzh.com/query-builder-component").attr("target","_blank").css({color:"#4676AE",font:"11px Calibri","text-decoration":"underline"}).appendTo(f)},_setOption:function(c,d){if(arguments.length==2){this.options[c]=d;if(c=="model"){this.clearQuery(false)}this._render();return this}else{return this.options[c]}},_clear:function(){this.element.html("")},_refreshByQuery:function(){var c=a("<div></div>");c.appendTo(this.element);c.RootPredicate({queryPanel:this,model:this.options.model});c.RootPredicate("init",this.options.query.root,null,null)},clearQuery:function(c){var d=this.options.query;d.root.linkType="All";d.root.enabled=true;d.root.conditions=[];d.columns=[];d.justsorted=[];if(c!==false){this.refresh()}},_createEntitiesMenu:function(){if(!this.options.model){return null}var d=a("<div></div>").hide().appendTo(this.element);var c=this;d.PopupMenu({items:this.options.entitiesList});return d},addNewCondition:function(f,d){var e=this;var c=e.element.find(".eqjs-qp-predicate-root:first");if(c){var g=c.data("RootPredicate");if(g){g.addNewCondition(f,d)}}},refresh:function(){this._render()},resize:function(){this._render()}});a.extend(a.eqjs.QueryPanel,{emptyQuery:function(){return{root:{linkType:"All",enabled:true,conditions:[]},columns:[],justsorted:[]}}})})(jQuery);(function(a,b){a.widget("eqjs.ValueEditor",{_expr:null,_linkElement:null,options:{queryPanel:null,model:null,condition:null,editor:null},init:function(c){this._expr=c;this.refresh()},getText:function(){if(!EQ.core.getText){return b}return EQ.core.getText.apply(this.options.queryPanel,arguments)},_getEmptyText:function(){return this.getText("MsgEmptyScalarValue")},_render:function(){this.clear();if(this.options.model&&this._expr){this._renderCommonPart();this._renderEditor();this._linkElement.text(this._getDisplayText())}},_getClassesToAdd:function(){return"eqjs-qp-condelement eqjs-qp-valueelement"},_renderCommonPart:function(){var c=this;c.element.addClass(c._getClassesToAdd());if(this.options.condition.enabled!==false){c._linkElement=a("<a></a>",{href:"javascript:void(0)",text:"-"}).appendTo(c.element);c._linkElement.click(function(){c._showEditor();return false})}else{c._linkElement=a("<span></span>",{text:"-"}).appendTo(c.element)}},_renderEditor:function(){},refresh:function(){this._render()},_showEditor:function(){},_setOption:function(c,d){if(arguments.length==2){this.options[c]=d;this._render();return this}else{return this.options[c]}},clear:function(){if(this._linkElement){this._linkElement.unbind()}this.element.removeClass();this.element.empty()},_setValue:function(c){this._expr.value=c;this._adjustExprText();this._linkElement.text(this._getDisplayText())},_getValue:function(){return this._expr.value},_adjustExprText:function(){if(this._expr.value&&this._expr.value!=""){this._expr.text=this._expr.value}else{this._expr.text=""}},_getDisplayText:function(){this._adjustExprText();if(this._expr.text&&this._expr.text!=""){return this._expr.text}else{if(this._expr.value&&this._expr.value!=""){return this._expr.value}else{return this._getEmptyText()}}},destroy:function(){this.clear();a.Widget.prototype.destroy.call(this)}})})(jQuery);(function(a,b){a.widget("eqjs.ValueEditor_EDIT",a.eqjs.ValueEditor,{_editBox:null,_editBoxClass:"eqjs-qp-ve-editbox",_renderEditor:function(){var c=this;c._editBox=a("<input />",{type:"text","class":c._editBoxClass,blur:function(d){if(c._editBox.is(":visible")){c._setValue(c._editBox.val());c._editBox.hide();d.stopPropagation();return false}},keydown:function(d){if(d.keyCode==13){if(c._editBox.is(":visible")){c._setValue(c._editBox.val());c._editBox.hide();d.stopPropagation();return false}}if(d.keyCode==27){c._editBox.hide();d.stopPropagation();return false}}}).appendTo(this.element.parent()).hide()},_showEditor:function(){var c=this;c._editBox.val(c._getValue()).css({left:c._linkElement.position().left,top:c._linkElement.position().top,position:"absolute",zIndex:"100000"}).show().focus()}})})(jQuery);(function(a,b){a.widget("eqjs.ValueEditor_DATETIME",a.eqjs.ValueEditor_EDIT,{_dateFormatInternal:"yy-mm-dd",_dateFormatValue:"mm/dd/yy",_dateFormatDisplay:"d MM, yy",_create:function(){if(this.options.queryPanel&&this.options.queryPanel.options.dateFormatValue){this._dateFormatValue=this.options.queryPanel.options.dateFormatValue}if(this.options.queryPanel&&this.options.queryPanel.options.dateFormatDisplay){this._dateFormatDisplay=this.options.queryPanel.options.dateFormatDisplay}},_renderEditor:function(){var c=this;c._editBox=a("<input />",{type:"text","class":"eqjs-qp-ve-editbox"}).appendTo(this.element.parent()).hide();c._editBox.datepicker({show:true,dateFormat:c._dateFormatValue,onSelect:function(e,d){c._setValue(c._convertDate(a(this).datepicker("getDate"),c._dateFormatInternal));c._editBox.hide();c._editBox.datepicker("hide")},onClose:function(e,d){c._editBox.hide()}})},_showEditor:function(){var c=this;c._editBox.val(c._convertDateString(c._getValue(),c._dateFormatInternal,c._dateFormatValue)).css({left:c._linkElement.position().left,top:c._linkElement.position().top,position:"absolute",zIndex:"100000"}).show().focus()},_adjustExprText:function(){if(this._expr.value&&this._expr.value!=""){this._expr.text=this._convertDateString(this._expr.value,this._dateFormatInternal,this._dateFormatDisplay)}else{this._expr.text=""}},_convertDateString:function(d,f,e){var c=a.datepicker.parseDate(f,d);return this._convertDate(c,e)},_convertDate:function(c,d){return a.datepicker.formatDate(d,c)}})})(jQuery);(function(a,b){a.widget("eqjs.ValueEditor_LIST",a.eqjs.ValueEditor,{_menuBlock:null,_menuItemsList:null,_emptyListText:"<empty list>",_getEmptyText:function(){if(this._menuItemsList&&(this._menuItemsList.length>0)){return this.getText("MsgEmptyListValue")}else{return this._emptyListText}},_renderEditor:function(){var c=this;c._fillMenuItemsList();c._renderMenuBlock()},_renderMenuBlock:function(){var c=this;if(!c._menuItemsList){return}c._menuBlock=a("<div></div>").hide().appendTo(c.element).PopupMenu({items:c._menuItemsList,onMenuItemSelected:function(d,e){c._setValue(e.menuItem.attr("data-id"));return false}})},_showEditor:function(){var c=this;c._menuBlock.PopupMenu("showMenu",c._linkElement)},_adjustExprText:function(){if(this._expr.value&&this._expr.value!=""&&this._expr.value!="undefined"&&this._menuBlock){var c=this._menuBlock.find('li[data-id="'+this._expr.value+'"] a');if(c){this._expr.text=c.text()}else{this._expr.text=""}}else{this._expr.text=""}},_fillMenuItemsList:function(){this._menuItemsList=this.options.editor.values.value}})})(jQuery);(function(a,b){a.widget("eqjs.ValueEditor_CUSTOMLIST",a.eqjs.ValueEditor_LIST,{_loaderElement:null,_renderEditor:function(){var c=this;c._linkElement.hide();c._loaderElement=a("<div></div>",{"class":"eqjs-qp-ve-loader"}).appendTo(c.element);c._fillMenuItemsList(function(){c._linkElement.text(c._getDisplayText());c._loaderElement.hide();c._linkElement.show();c._renderMenuBlock()})},_fillMenuItemsList:function(e){var c=this,d=this.options.editor.name;if(EQ.core.constLists[d]){c._menuItemsList=EQ.core.constLists[d];if(e){e()}}else{if(d==="EntityTree"){c._menuItemsList=c.options.queryPanel.options.entitiesList;if(e){e()}}else{if(c.options.queryPanel.options.listRequest){c.options.queryPanel.options.listRequest(d,function(f){c._menuItemsList=f;if(e){e()}})}}}}})})(jQuery);(function(a,b){a.widget("eqjs.ValueEditor_SQLLIST",a.eqjs.ValueEditor_CUSTOMLIST,{_fillMenuItemsList:function(d){var c=this;if(c.options.queryPanel.options.sqlListRequest){c.options.queryPanel.options.sqlListRequest(c.options.editor.sql,function(e){c._menuItemsList=e;if(d){d()}})}}})})(jQuery);(function(a,b){a.widget("eqjs.ValueEditor_SUBQUERY",a.eqjs.ValueEditor,{_dialogBlock:null,_queryPanelBlock:null,_getEmptyText:function(){return this.getText("MsgSubQueryValue")},_renderEditor:function(){var c=this;c._dialogBlock=a("<div></div>",{"class":"eqjs-qp-ve-subquery"}).appendTo(c.element);c._queryPanelBlock=a('<div class="eqjs-qp-ve-subquery-qp"></div>').appendTo(c._dialogBlock);c._dialogBlock.dialog({autoOpen:false,title:c.getText("SubQueryDialogTitle"),modal:true,width:600,minHeight:360,buttons:[{text:c.getText("ButtonOK"),click:function(){c._expr.value=c._queryPanelBlock.QueryPanel("option","query");a(this).dialog("close")}},{text:c.getText("ButtonCancel"),click:function(){a(this).dialog("close")}}],close:function(d,e){c._queryPanelBlock.QueryPanel("destroy")}})},_showEditor:function(){var c=this;c._queryPanelBlock.empty();if(!c._expr.value){c._expr.value=a.eqjs.QueryPanel.emptyQuery()}c._queryPanelBlock.QueryPanel({isSubQuery:true,model:c.options.model,query:c._expr.value,showRootRow:c.options.queryPanel.options.showRootRow,showAddRow:c.options.queryPanel.options.showAddRow,dateFormatValue:c.options.queryPanel.options.dateFormatValue,dateFormatDisplay:c.options.queryPanel.options.dateFormatDisplay});c._dialogBlock.dialog("open")},_getDisplayText:function(){return this._getEmptyText()}})})(jQuery);(function(a,b){a.widget("eqjs.PredicateLinkType",a.eqjs.ValueEditor_LIST,{_predicate:null,options:{queryPanel:null,condition:null},init:function(c){this._predicate=c;this.options.condition=this._predicate;this.refresh()},_render:function(){this.clear();if(this._predicate){this._renderCommonPart();if(this._predicate.enabled!==false){this._renderEditor()}this._linkElement.text(this._getDisplayText())}},_fillMenuItemsList:function(){this._menuItemsList=EQ.core.predicateLinkTypeList},_setValue:function(c){this._predicate.linkType=c;var d=this._adjustText();this._linkElement.text(d!=""?d:this._getEmptyText())},_adjustText:function(){if(this._predicate.linkType&&this._predicate.linkType!=""&&this._predicate.linkType!="undefined"){var c=this._menuBlock.find('li[data-id="'+this._predicate.linkType+'"] a');if(c){return c.text()}else{return""}}else{return""}},_getClassesToAdd:function(){return"eqjs-qp-predelement eqjs-qp-predvalueelement"},_getDisplayText:function(){var d=this,c=this._getEmptyText();if(d._predicate.linkType&&d._predicate.linkType!=""){a.each(EQ.core.predicateLinkTypeList,function(){if(this.id===d._predicate.linkType){c=this.text}})}return c}})})(jQuery);