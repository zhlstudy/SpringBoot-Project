$(function() {
	/*商品小图片加鼠标移入加边框、移出移除边框*/
	$(".img-small").hover(function() {
			$(this).css("border", "1px solid #4288c3");
		},
		function() {
			$(this).css("border", "");
		})
	//点击时变化大图片
	$(".img-small").click(function() {
			//获得点击的小图片数据
			var n = $(this).attr("data");
			//所有大图隐藏
			$(".img-big").hide();
			//显示点击的小图对应的大图
			$(".img-big[data='" + n + "']").show();
		})
})
