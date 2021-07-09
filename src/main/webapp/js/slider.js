$(".slick-container").slick({
    slidesToShow: 3,
    slideToScroll: 1,
    dots: true,
    autoplay: true,
    autoplaySpeed: 2500,
    centerMode: true,
    pauseOnHover: false,
    pauseOnFocus: false,
    centerPadding: '50px',
    responsive: [
        {
            breakpoint: 1232,
            settings: {
                arrows: false,
                slidesToShow: 2
            }
        },
        {
            breakpoint: 848,
            settings: {
                arrows: false,
                slidesToShow: 1
            }
        }
    ]
})