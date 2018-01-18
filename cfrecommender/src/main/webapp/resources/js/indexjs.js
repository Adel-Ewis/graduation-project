/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


jQuery(function ($) {
    $('#contact textarea').css('min-height', '150px');


    ///make navbar compact when scrolling down
    var isCompact = false;
    $(window)
            .on('scroll.scroll_nav', function () {
                var scroll = $(window).scrollTop();
                var h = $(window).height();
                var body_sH = document.body.scrollHeight;
                if (scroll > parseInt(h / 4) || (scroll > 0 && body_sH >= h && h + scroll >= body_sH - 1)) {//|| for smaller pages, when reached end of page
                    if (!isCompact)
                        $('.navbar').addClass('navbar-compact');
                    isCompact = true;
                } else {
                    $('.navbar').removeClass('navbar-compact');
                    isCompact = false;
                }
            }).triggerHandler('scroll.scroll_nav');


    //optinal: when window is too small change background presentation
    $(window)
            .on('resize.bg_update', function () {
                var width = $(window).width();

                if (width < 992) {
                    $('.img-main-background').addClass('hide');
                    $('.jumbotron').addClass('has-background');
                } else {
                    $('.img-main-background').removeClass('hide');
                    $('.jumbotron').removeClass('has-background');
                }
            }).triggerHandler('resize.bg_update');


    //animated scroll to a section
    $(document).on('click', '#navbar a', function () {
        var href = $(this).attr('href');
        var target = $(href);
        if (target.length === 1) {
            var offset = target.offset();
            var top = parseInt(Math.max(offset.top - 30, 0));
            $('html,body').animate({scrollTop: top}, 250);
        }
    });

});