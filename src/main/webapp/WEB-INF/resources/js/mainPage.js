function hideOrShow(node) {
	$(node).next().slideToggle();
}

function changePath(node) {
	var iframeNode = document.getElementById("div-right-iframe");
	iframeNode.src = node.title;
}


layui.use('element', function(){
	var element = layui.element;
});