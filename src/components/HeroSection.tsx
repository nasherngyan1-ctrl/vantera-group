import React from 'react';
import { ArrowRight } from 'lucide-react';
import { assetUrl, VanteraCopy } from '../i18n/vanteraStrings';

interface HeroSectionProps {
  copy: VanteraCopy;
  onExploreBusiness: () => void;
  onContactTeam: () => void;
}

export const HeroSection: React.FC<HeroSectionProps> = ({
  copy,
  onExploreBusiness,
  onContactTeam,
}) => {
  return (
    <section id="home" className="hero-section">
      <img
        src={assetUrl('assets/images/img_corporate_headquarters.jpg')}
        alt={copy.heroImageAlt}
        className="hero-bg-img"
      />
      <div className="hero-scrim" aria-hidden="true" />

      <div className="container hero-content">
        <div className="eyebrow">{copy.heroEyebrow}</div>
        <div>
          <span className="hero-chip">{copy.heroDemoChip}</span>
        </div>
        <h1 className="hero-title">{copy.heroHeadline}</h1>
        <p className="hero-subtitle">{copy.heroSubtitle}</p>

        <div className="hero-actions">
          <button type="button" className="btn btn-terracotta" onClick={onExploreBusiness}>
            <span>{copy.heroPrimaryCta}</span>
            <ArrowRight size={18} className="rtl-flip" aria-hidden="true" />
          </button>
          <button type="button" className="btn btn-outline-light" onClick={onContactTeam}>
            {copy.heroSecondaryCta}
          </button>
        </div>
      </div>
    </section>
  );
};
