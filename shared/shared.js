/* ========================================
   shared.js - Dynamic components for jonathanpufall.com
   ======================================== */

(function () {
  'use strict';

  // ---- Navigation Config ----
  var NAV_ITEMS = [
    { label: 'Home',               href: 'index.html',               id: 'home' },
    { label: 'About',              href: 'about.html',               id: 'about' },
    { label: 'Business Portfolio',  href: 'business_portfolio.html',  id: 'business' },
    { label: 'Artistic Portfolio',  href: 'artistic_portfolio.html',  id: 'artistic' },
    { label: 'Technical Portfolio', href: 'technical_portfolio.html', id: 'technical' },
    { label: 'Contact',            href: 'contact.html',             id: 'contact' }
  ];

  var CURRENT_YEAR = new Date().getFullYear();

  // ---- Read page config from <body> data attributes ----
  var body      = document.body;
  var pageId    = body.dataset.pageId || '';
  var pageTitle = body.dataset.pageTitle || 'Jonathan Pufall';
  var pageSub   = body.dataset.pageSubtitle || '';

  // ---- Build Header ----
  function buildHeader() {
    var header = document.createElement('header');
    header.innerHTML =
      '<div class="logo">' +
        '<a href="index.html">' +
          '<img src="assets/images/logo.png" alt="JP Logo">' +
        '</a>' +
      '</div>' +
      '<div>' +
        '<h1>' + pageTitle + '</h1>' +
        (pageSub ? '<p>' + pageSub + '</p>' : '') +
      '</div>';
    return header;
  }

  // ---- Build Nav ----
  function buildNav() {
    var nav = document.createElement('nav');
    var items = '';

    for (var i = 0; i < NAV_ITEMS.length; i++) {
      var item = NAV_ITEMS[i];
      var activeClass = item.id === pageId ? ' class="active"' : '';
      items += '<li><a href="' + item.href + '"' + activeClass + '>' + item.label + '</a></li>\n';
    }

    nav.innerHTML =
      '<div class="nav-toggle-wrapper">' +
        '<span class="menu-label">Menu</span>' +
        '<button class="nav-toggle" id="nav-toggle" aria-label="Toggle navigation">\u2630</button>' +
      '</div>' +
      '<ul class="nav-links" id="nav-links">' +
        items +
      '</ul>';
    return nav;
  }

  // ---- Build Footer ----
  function buildFooter() {
    var footer = document.createElement('footer');
    footer.innerHTML = '<p>&copy; ' + CURRENT_YEAR + ' Jonathan Pufall | jonathanpufall.com</p>';
    return footer;
  }

  // ---- Inject Components ----
  var firstChild = body.firstElementChild || body.firstChild;
  var header = buildHeader();
  var nav = buildNav();

  body.insertBefore(nav, firstChild);
  body.insertBefore(header, nav);
  body.appendChild(buildFooter());

  // ---- Nav Toggle (mobile hamburger) ----
  var toggle = document.getElementById('nav-toggle');
  var navLinks = document.getElementById('nav-links');

  if (toggle && navLinks) {
    toggle.addEventListener('click', function () {
      navLinks.classList.toggle('show');
    });

    // Close nav when a link is clicked (mobile UX)
    var links = navLinks.querySelectorAll('a');
    for (var j = 0; j < links.length; j++) {
      links[j].addEventListener('click', function () {
        navLinks.classList.remove('show');
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

    document.addEventListener('click', function (e) {
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
