// Carousel Functionality for Artistic Portfolio Page

document.addEventListener("DOMContentLoaded", () => {
  const track = document.querySelector(".carousel-track");
  const slides = document.querySelectorAll(".carousel-slide");
  const dots = document.querySelectorAll(".carousel-dot");
  const prevButton = document.querySelector(".prev");
  const nextButton = document.querySelector(".next");
  const wrapper = document.querySelector(".carousel-wrapper");

  let currentIndex = 0;
  let autoSlideInterval;

  function updateSlidePosition() {
    const offset = -currentIndex * 100;
    track.style.transform = `translateX(${offset}%)`;
    updateDots();
  }

  function moveSlide(step) {
    currentIndex = (currentIndex + step + slides.length) % slides.length;
    updateSlidePosition();
  }

  function startAutoSlide() {
    autoSlideInterval = setInterval(() => moveSlide(1), 5000);
  }

  function stopAutoSlide() {
    clearInterval(autoSlideInterval);
  }

  function updateDots() {
    dots.forEach(dot => dot.classList.remove("active"));
    dots[currentIndex].classList.add("active");
  }

  dots.forEach((dot, index) => {
    dot.addEventListener("click", () => {
      currentIndex = index;
      updateSlidePosition();
    });
  });

  prevButton.addEventListener("click", () => moveSlide(-1));
  nextButton.addEventListener("click", () => moveSlide(1));

  wrapper.addEventListener("mouseenter", stopAutoSlide);
  wrapper.addEventListener("mouseleave", startAutoSlide);

  updateSlidePosition();
  startAutoSlide();
});