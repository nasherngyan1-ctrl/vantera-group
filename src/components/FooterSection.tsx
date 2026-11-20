import React from 'react';
import { VanteraCopy } from '../i18n/vanteraStrings';
import { DivisionItem, NavSectionId } from '../types/vantera';
import { VanteraWordmark } from './Header';

interface FooterSectionProps {
  copy: VanteraCopy;
  onSelectSection: (section: NavSectionId) => void;
  onSelectDivision: (division: DivisionItem) => void;
}

export const FooterSection: React.FC<FooterSectionProps> = ({
  copy,
  onSelectSection,
  onSelectDivision,
}) => {
  const navItems: Array<{ id: NavSectionId; label: string }> = [
    { id: 'home', label: copy.navHome },
    { id: 'about', label: copy.navAbout },
    { id: 'services', label: copy.navServices },
    { id: 'projects', label: copy.navProjects },
    { id: 'contact', label: copy.navContact },
  ];

  return (
    <footer className="site-footer">
      <div className="container">
        <div className="footer-grid">
          <div>
            <VanteraWordmark
              wordmark={copy.brandWordmark}
              subword={copy.brandSubword}
              lightOnDark
            />
            <p style={{ marginTop: 12, fontSize: '0.88rem', color: 'rgba(233,229,220,0.75)' }}>
              {copy.footerTagline}
            </p>
          </div>

          <div>
            <h3 className="footer-heading">{copy.footerNavHeading}</h3>
            <div className="footer-links">
              {navItems.map((item) => (
                <button
                  key={item.id}
                  type="button"
                  className="footer-link-btn"
                  onClick={() => onSelectSection(item.id)}
                >
                  {item.label}
                </button>
              ))}
            </div>
          </div>

          <div>
            <h3 className="footer-heading">{copy.footerDivisionsHeading}</h3>
            <div className="footer-links">
              {copy.divisions.map((division) => (
                <button
                  key={division.id}
                  type="button"
                  className="footer-link-btn"
                  onClick={() => onSelectDivision(division)}
                >
                  {division.title}
                </button>
              ))}
            </div>
          </div>

          <div>
            <h3 className="footer-heading">{copy.footerContactHeading}</h3>
            <div style={{ display: 'flex', flexDirection: 'column', gap: 6, fontSize: '0.92rem' }}>
              <span>{copy.contactLocationValue}</span>
              <span>{copy.contactEmailValue}</span>
              <span style={{ color: '#C47752', fontSize: '0.8rem' }}>{copy.contactEmailDemoNote}</span>
            </div>
          </div>
        </div>

        <div className="footer-bottom">
          <div>{copy.footerCopyright}</div>
          <div>{copy.footerDemoDisclosure}</div>
        </div>
      </div>
    </footer>
  );
};
