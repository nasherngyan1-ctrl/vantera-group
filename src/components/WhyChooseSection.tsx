import React from 'react';
import { VanteraCopy } from '../i18n/vanteraStrings';

interface WhyChooseSectionProps {
  copy: VanteraCopy;
}

export const WhyChooseSection: React.FC<WhyChooseSectionProps> = ({ copy }) => {
  return (
    <section className="section-pad why-section reveal">
      <div className="container">
        <div className="eyebrow">{copy.whyEyebrow}</div>
        <h2 className="section-title">{copy.whyHeadline}</h2>
        <p className="section-subtitle">{copy.whySubtitle}</p>

        <div className="cards-2-grid">
          {copy.whyPillars.map((pillar) => (
            <article key={pillar.indexLabel} className="pillar-card">
              <div className="pillar-head">
                <span className="pillar-num">{pillar.indexLabel}</span>
                <span className="pillar-rule" aria-hidden="true" />
              </div>
              <h3 className="pillar-title">{pillar.title}</h3>
              <p className="pillar-desc">{pillar.description}</p>
            </article>
          ))}
        </div>
      </div>
    </section>
  );
};
