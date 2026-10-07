/* ========================================
   shared.js - Shared behavior for jonathanpufall.com
   (header, nav and footer markup live in each page's HTML)
   ======================================== */

(function () {
  'use strict';

  // ---- Footer Year ----
  var yearEl = document.getElementById('footer-year');
  if (yearEl) {
    yearEl.textContent = new Date().getFullYear();
  }

  // ---- Nav Toggle (mobile hamburger) ----
  var toggle = document.getElementById('nav-toggle');
  var navLinks = document.getElementById('nav-links');

  if (toggle && navLinks) {
    var setOpen = function (open) {
      navLinks.classList.toggle('show', open);
      toggle.setAttribute('aria-expanded', open ? 'true' : 'false');
    };

    toggle.addEventListener('click', function () {
      setOpen(!navLinks.classList.contains('show'));
    });

    // Close nav when a link is clicked (mobile UX)
    var links = navLinks.querySelectorAll('a');
    for (var j = 0; j < links.length; j++) {
      links[j].addEventListener('click', function () {
        setOpen(false);
      });
    }
  }

  // ---- Google Analytics ----
  var gaScript = document.createElement('script');
  gaScript.async = true;
  gaScript.src = 'https://www.googletagmanager.com/gtag/js?id=G-SF82PDKYSH';
  document.head.appendChild(gaScript);

  window.dataLayer = window.dataLayer || [];
  function gtag() { window.dataLayer.push(arguments); }
  gtag('js', new Date());
  gtag('config', 'G-SF82PDKYSH');

  // ---- Scroll Reveal via IntersectionObserver ----
  (function initScrollReveal() {
    var revealElements = document.querySelectorAll('[data-reveal], [data-reveal-stagger]');
    if (!revealElements.length) return;

    // Respect reduced motion
    if (window.matchMedia && window.matchMedia('(prefers-reduced-motion: reduce)').matches) {
      for (var k = 0; k < revealElements.length; k++) {
        revealElements[k].classList.add('revealed');
      }
      return;
    }

    var observer = new IntersectionObserver(
      function (entries) {
        for (var m = 0; m < entries.length; m++) {
          if (entries[m].isIntersecting) {
            entries[m].target.classList.add('revealed');
            observer.unobserve(entries[m].target);
          }
        }
      },
      {
        threshold: 0.12,
        rootMargin: '0px 0px -40px 0px'
      }
    );

    for (var n = 0; n < revealElements.length; n++) {
      observer.observe(revealElements[n]);
    }
  })();

})();
