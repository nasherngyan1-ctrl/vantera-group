import React from 'react';
import { ArrowRight } from 'lucide-react';
import { assetUrl, VanteraCopy } from '../i18n/vanteraStrings';
import { DivisionItem } from '../types/vantera';

interface DivisionsSectionProps {
  copy: VanteraCopy;
  onSelectDivision: (division: DivisionItem) => void;
}

export const DivisionsSection: React.FC<DivisionsSectionProps> = ({
  copy,
  onSelectDivision,
}) => {
  return (
    <section id="services" className="section-pad divisions-section reveal">
      <div className="container">
        <div className="eyebrow">{copy.divisionsEyebrow}</div>
        <h2 className="section-title">{copy.divisionsHeadline}</h2>
        <p className="section-subtitle">{copy.divisionsSubtitle}</p>

        <div className="cards-3-grid">
          {copy.divisions.map((division) => (
            <button
              key={division.id}
              type="button"
              className="editorial-card"
              onClick={() => onSelectDivision(division)}
            >
              <div className="card-media">
                <img src={assetUrl(division.imagePath)} alt={division.title} loading="lazy" />
                <div className="card-media-scrim" aria-hidden="true" />
                <span className="card-index-badge">{division.numberLabel}</span>
                <span className="card-media-subtitle">{division.subtitle}</span>
              </div>

              <div className="card-body">
                <h3 className="card-title">{division.title}</h3>
                <p className="card-desc">{division.description}</p>
                <div className="card-footer-link">
                  <span>{copy.divisionExploreLabel}</span>
                  <ArrowRight size={17} className="rtl-flip" aria-hidden="true" />
                </div>
              </div>
            </button>
          ))}
        </div>
      </div>
    </section>
  );
};
