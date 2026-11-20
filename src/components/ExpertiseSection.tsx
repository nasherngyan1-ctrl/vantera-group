import React from 'react';
import { assetUrl, VanteraCopy } from '../i18n/vanteraStrings';

interface ExpertiseSectionProps {
  copy: VanteraCopy;
}

export const ExpertiseSection: React.FC<ExpertiseSectionProps> = ({ copy }) => {
  return (
    <section className="section-pad expertise-section reveal">
      <div className="container">
        <div className="eyebrow">{copy.expertiseEyebrow}</div>
        <h2 className="section-title">{copy.expertiseHeadline}</h2>
        <p className="expertise-lead">{copy.expertiseBody}</p>

        <div className="expertise-photos">
          <div>
            <div className="frame-box">
              <img
                src={assetUrl('assets/images/img_engineering_team.jpg')}
                alt={copy.expertiseCaptionEngineering}
                loading="lazy"
              />
            </div>
            <div className="expertise-caption">{copy.expertiseCaptionEngineering}</div>
          </div>

          <div>
            <div className="frame-box">
              <img
                src={assetUrl('assets/images/img_corporate_office.jpg')}
                alt={copy.expertiseCaptionOffice}
                loading="lazy"
              />
            </div>
            <div className="expertise-caption">{copy.expertiseCaptionOffice}</div>
          </div>
        </div>
      </div>
    </section>
  );
};
