jQuery(document).ready(function ($) {
	$(".scroll").click(function (event) {
		event.preventDefault();
		$('html,body').animate({scrollTop: $(this.hash).offset().top}, 500);
	});
	$('.menu').on('change', function () {
		var url = $(this).val();
		if (url) {
			window.location = url;
		}
		return false;
	});
});