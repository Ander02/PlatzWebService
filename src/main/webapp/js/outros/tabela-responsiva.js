$.fn.pageMe = function (opts) {
    var $this = this,
            defaults = {
                perPage: 5,
                showPrevNext: false,
                hidePageNumbers: false
            },
    settings = $.extend(defaults, opts);

    var listElement = $this;
    var perPage = settings.perPage;
    var children = listElement.children();
    var pager = $('.pager');

    if (typeof settings.childSelector != "undefined") {
        children = listElement.find(settings.childSelector);
    }

    if (typeof settings.pagerSelector != "undefined") {
        pager = $(settings.pagerSelector);
    }

    var numItems = children.size();
    var numPages = Math.ceil(numItems / perPage);

    pager.data("curr", 0);

    if (settings.showPrevNext) {
        $('<li class="page-item"><a href="#" class="prev_link page-link">«</a></li>').appendTo(pager);
    }

    var curr = 0;
    while (numPages > curr && (settings.hidePageNumbers == false)) {
        $('<li class="page-item"><a href="#" class="page_link page-link">' + (curr + 1) + '</a></li>').appendTo(pager);
        curr++;
    }

    if (settings.showPrevNext) {
        $('<li class="page-item"><a href="#" class="next_link page-link ">»</a></li>').appendTo(pager);
    }

    pager.find('.page_link:first').addClass('active');
    pager.find('.prev_link').hide();
    if (numPages <= 1) {
        pager.find('.next_link').hide();
    }
    pager.children().eq(1).addClass("active");

    children.hide();
    children.slice(0, perPage).show();

    pager.find('li .page_link').click(function () {
        var clickedPage = $(this).html().valueOf() - 1;
        goTo(clickedPage, perPage);
        return false;
    });
    pager.find('li .prev_link').click(function () {
        previous();
        return false;
    });
    pager.find('li .next_link').click(function () {
        next();
        return false;
    });

    function previous() {
        var goToPage = parseInt(pager.data("curr")) - 1;
        goTo(goToPage);
    }

    function next() {
        goToPage = parseInt(pager.data("curr")) + 1;
        goTo(goToPage);
    }

    function goTo(page) {
        var startAt = page * perPage,
                endOn = startAt + perPage;

        children.css('display', 'none').slice(startAt, endOn).show();

        if (page >= 1) {
            pager.find('.prev_link').show();
        } else {
            pager.find('.prev_link').hide();
        }

        if (page < (numPages - 1)) {
            pager.find('.next_link').show();
        } else {
            pager.find('.next_link').hide();
        }

        pager.data("curr", page);
        pager.children().removeClass("active");
        pager.children().eq(page + 1).addClass("active");

    }
};

$(document).ready(function () {

    $('#myTable').pageMe({pagerSelector: '#myPager', showPrevNext: true, hidePageNumbers: false, perPage: 4});
    $('#myTable2').pageMe({pagerSelector: '#myPager2', showPrevNext: true, hidePageNumbers: false, perPage: 4});
    $('#myTable3').pageMe({pagerSelector: '#myPager3', showPrevNext: true, hidePageNumbers: false, perPage: 4});
    $('#myTable4').pageMe({pagerSelector: '#myPager4', showPrevNext: true, hidePageNumbers: false, perPage: 4});
    $('#myTable5').pageMe({pagerSelector: '#myPager5', showPrevNext: true, hidePageNumbers: false, perPage: 4});
    $('#myTable6').pageMe({pagerSelector: '#myPager6', showPrevNext: true, hidePageNumbers: false, perPage: 4});
    $('#myTable7').pageMe({pagerSelector: '#myPager7', showPrevNext: true, hidePageNumbers: false, perPage: 4});
    $('#myTable8').pageMe({pagerSelector: '#myPager8', showPrevNext: true, hidePageNumbers: false, perPage: 4});
    $('#myTable9').pageMe({pagerSelector: '#myPager9', showPrevNext: true, hidePageNumbers: false, perPage: 4});
    

});