import React, { useEffect, useRef, useState } from 'react';
import { ChevronDown, Globe, Menu, X } from 'lucide-react';
import { LANGUAGE_OPTIONS, VanteraCopy } from '../i18n/vanteraStrings';
import { AppLanguage, NavSectionId } from '../types/vantera';

interface HeaderProps {
  copy: VanteraCopy;
  currentLanguage: AppLanguage;
  activeSection: NavSectionId;
  isMobileMenuOpen: boolean;
  onSelectSection: (section: NavSectionId) => void;
  onSelectLanguage: (language: AppLanguage) => void;
  onToggleMobileMenu: (open: boolean) => void;
}

export const VanteraWordmark: React.FC<{
  wordmark: string;
  subword: string;
  lightOnDark?: boolean;
  onClick?: () => void;
}> = ({ wordmark, subword, lightOnDark = false, onClick }) => {
  const className = `wordmark${lightOnDark ? ' wordmark--light' : ''}`;
  if (onClick) {
    return (
      <button type="button" className={className} onClick={onClick} aria-label={`${wordmark} ${subword}`}>
        <span className="wordmark-main">{wordmark}</span>
        <span className="wordmark-sub">{subword}</span>
      </button>
    );
  }
  return (
    <div className={className}>
      <span className="wordmark-main">{wordmark}</span>
      <span className="wordmark-sub">{subword}</span>
    </div>
  );
};

export const Header: React.FC<HeaderProps> = ({
  copy,
  currentLanguage,
  activeSection,
  isMobileMenuOpen,
  onSelectSection,
  onSelectLanguage,
  onToggleMobileMenu,
}) => {
  const [langMenuOpen, setLangMenuOpen] = useState(false);
  const langMenuRef = useRef<HTMLDivElement>(null);

  const currentLangObj =
    LANGUAGE_OPTIONS.find((item) => item.code === currentLanguage) ?? LANGUAGE_OPTIONS[0];

  const navItems: Array<{ id: NavSectionId; label: string }> = [
    { id: 'home', label: copy.navHome },
    { id: 'about', label: copy.navAbout },
    { id: 'services', label: copy.navServices },
    { id: 'projects', label: copy.navProjects },
    { id: 'contact', label: copy.navContact },
  ];

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (langMenuRef.current && !langMenuRef.current.contains(event.target as Node)) {
        setLangMenuOpen(false);
      }
    };
    const handleEscape = (event: KeyboardEvent) => {
      if (event.key === 'Escape') {
        setLangMenuOpen(false);
        onToggleMobileMenu(false);
      }
    };
    document.addEventListener('mousedown', handleClickOutside);
    document.addEventListener('keydown', handleEscape);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
      document.removeEventListener('keydown', handleEscape);
    };
  }, [onToggleMobileMenu]);

  return (
    <header className="site-header">
      <div className="container header-inner">
        <VanteraWordmark
          wordmark={copy.brandWordmark}
          subword={copy.brandSubword}
          onClick={() => {
            onToggleMobileMenu(false);
            onSelectSection('home');
          }}
        />

        <nav className="desktop-nav" aria-label="Main navigation">
          {navItems.map((item) => (
            <button
              key={item.id}
              type="button"
              className={`nav-link${activeSection === item.id ? ' is-active' : ''}`}
              onClick={() => onSelectSection(item.id)}
            >
              {item.label}
            </button>
          ))}
        </nav>

        <div className="header-actions">
          <div className="lang-wrapper" ref={langMenuRef}>
            <button
              type="button"
              className="lang-btn"
              aria-haspopup="listbox"
              aria-expanded={langMenuOpen}
              aria-label={copy.languageSelectorLabel}
              onClick={() => setLangMenuOpen((prev) => !prev)}
            >
              <Globe size={16} aria-hidden="true" />
              <span>{currentLangObj.shortCode}</span>
              <ChevronDown size={15} aria-hidden="true" />
            </button>

            {langMenuOpen && (
              <div className="lang-dropdown" role="listbox" aria-label={copy.languageSelectorLabel}>
                {LANGUAGE_OPTIONS.map((option) => (
                  <button
                    key={option.code}
                    type="button"
                    role="option"
                    aria-selected={option.code === currentLanguage}
                    className={`lang-option${option.code === currentLanguage ? ' is-selected' : ''}`}
                    onClick={() => {
                      onSelectLanguage(option.code);
                      setLangMenuOpen(false);
                    }}
                  >
                    <span>{option.nativeLabel}</span>
                    <span className="lang-code">{option.code.toUpperCase()}</span>
                  </button>
                ))}
              </div>
            )}
          </div>

          <button
            type="button"
            className="btn btn-terracotta header-contact-desktop"
            onClick={() => onSelectSection('contact')}
          >
            {copy.contactUsCta}
          </button>

          <button
            type="button"
            className="menu-toggle-btn"
            aria-expanded={isMobileMenuOpen}
            aria-label={isMobileMenuOpen ? copy.closeMenuLabel : copy.openMenuLabel}
            onClick={() => onToggleMobileMenu(!isMobileMenuOpen)}
          >
            {isMobileMenuOpen ? <X size={20} /> : <Menu size={20} />}
          </button>
        </div>
      </div>

      {isMobileMenuOpen && (
        <div className="mobile-drawer">
          <nav className="mobile-nav-list" aria-label="Mobile navigation">
            {navItems.map((item, index) => (
              <button
                key={item.id}
                type="button"
                className={`mobile-nav-item${activeSection === item.id ? ' is-active' : ''}`}
                onClick={() => {
                  onToggleMobileMenu(false);
                  onSelectSection(item.id);
                }}
              >
                <span>{item.label}</span>
                <span className="mobile-nav-index">0{index + 1}</span>
              </button>
            ))}
          </nav>

          <div className="mobile-lang-row">
            {LANGUAGE_OPTIONS.map((option) => (
              <button
                key={option.code}
                type="button"
                className={`mobile-lang-pill${option.code === currentLanguage ? ' is-selected' : ''}`}
                onClick={() => onSelectLanguage(option.code)}
              >
                {option.nativeLabel}
              </button>
            ))}
          </div>

          <button
            type="button"
            className="btn btn-terracotta mobile-drawer-cta"
            onClick={() => {
              onToggleMobileMenu(false);
              onSelectSection('contact');
            }}
          >
            {copy.contactUsCta}
          </button>
        </div>
      )}
    </header>
  );
};
