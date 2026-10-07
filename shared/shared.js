/* ========================================
   shared.js - Shared behavior for jonathanpufall.com
   (header, nav and footer markup live in each page's HTML)
   ======================================== */

(function () {
  'use strict';

  var body = document.body;

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

  // ---- Page Transition ----
  (function initPageTransition() {
    var overlay = document.createElement('div');
    overlay.className = 'page-transition-overlay entering';
    body.appendChild(overlay);

    overlay.addEventListener('animationend', function () {
      overlay.classList.remove('entering');
    });

    // Clear the overlay when the page is restored from the back/forward cache
    window.addEventListener('pageshow', function (e) {
      if (e.persisted) {
        overlay.classList.remove('fade-in', 'entering');
      }
    });

    document.addEventListener('click', function (e) {
      // Let the browser handle new-tab/new-window clicks
      if (e.defaultPrevented || e.button !== 0 ||
          e.ctrlKey || e.metaKey || e.shiftKey || e.altKey) {
        return;
      }

      var link = e.target.closest ? e.target.closest('a[href]') : null;
      if (!link) return;

      var href = link.getAttribute('href');
      if (!href) return;

      // Skip external links, anchors, downloads, new-tab links
      if (href.charAt(0) === '#' ||
          href.indexOf('http') === 0 ||
          href.indexOf('mailto:') === 0 ||
          link.hasAttribute('download') ||
          link.getAttribute('target') === '_blank') {
        return;
      }

      e.preventDefault();
      overlay.classList.add('fade-in');

      setTimeout(function () {
        window.location.href = href;
      }, 300);
    });
  })();

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
