document.addEventListener("DOMContentLoaded", () => {
  const root = document.getElementById("gallery-carousel");
  if (!root || root.dataset.initialized === "true") return;   // avoid double init (which breaks timing)
  root.dataset.initialized = "true";

  const carousel = root.querySelector(".carousel");
  const track    = root.querySelector(".carousel-track");
  const slides   = Array.from(root.querySelectorAll(".carousel-slide"));
  const prevBtn  = root.querySelector(".carousel-btn.prev");
  const nextBtn  = root.querySelector(".carousel-btn.next");
  const dotsWrap = root.querySelector(".carousel-dots");

  if (!carousel || !track || slides.length === 0 || !prevBtn || !nextBtn) {
    console.warn("[carousel] missing elements");
    return;
  }

  let index = 0;
  let auto = null;
  const AUTO_MS = 5000;

  // build dots
  dotsWrap.innerHTML = "";
  slides.forEach((_, i) => {
    const b = document.createElement("button");
    b.type = "button";
    b.setAttribute("role", "tab");
    b.setAttribute("aria-label", `Go to slide ${i + 1}`);
    b.addEventListener("click", () => goTo(i));
    dotsWrap.appendChild(b);
  });
  const dots = Array.from(dotsWrap.querySelectorAll("button"));

  function updateUI() {
    track.style.transform = `translateX(${-index * 100}%)`;
    dots.forEach((d, i) => d.setAttribute("aria-selected", i === index ? "true" : "false"));
  }
  function goTo(i) { index = (i + slides.length) % slides.length; updateUI(); }
  function next()   { goTo(index + 1); }
  function prev()   { goTo(index - 1); }

  // buttons (no optional chaining; fail loudly if not found)
  prevBtn.addEventListener("click", prev, { passive: true });
  nextBtn.addEventListener("click", next, { passive: true });

  // pause on hover
  function startAuto() { stopAuto(); auto = setInterval(next, AUTO_MS); }
  function stopAuto()  { if (auto) { clearInterval(auto); auto = null; } }
  carousel.addEventListener("mouseenter", stopAuto);
  carousel.addEventListener("mouseleave", startAuto);

  // init
  updateUI();
  startAuto();
});
