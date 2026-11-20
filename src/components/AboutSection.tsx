import React from 'react';
import { MapPin } from 'lucide-react';
import { assetUrl, VanteraCopy } from '../i18n/vanteraStrings';

interface AboutSectionProps {
  copy: VanteraCopy;
}

export const AboutSection: React.FC<AboutSectionProps> = ({ copy }) => {
  return (
    <section id="about" className="section-pad about-section reveal">
      <div className="container split-grid">
        <div className="about-copy">
          <div className="eyebrow">{copy.aboutEyebrow}</div>
          <h2 className="section-title">{copy.aboutHeadline}</h2>
          <p>{copy.aboutParagraph1}</p>
          <p>{copy.aboutParagraph2}</p>

          <div className="about-location">
            <MapPin size={20} color="#C47752" aria-hidden="true" />
            <span>{copy.aboutLocationBadge}</span>
          </div>
        </div>

        <div>
          <div className="frame-box">
            <img
              src={assetUrl('assets/images/img_city_skyline.jpg')}
              alt={copy.aboutImageCaption}
              loading="lazy"
            />
            <div className="frame-accent-bar" aria-hidden="true" />
          </div>
          <p className="frame-caption">{copy.aboutImageCaption}</p>
        </div>
      </div>
    </section>
  );
};
