import React from 'react';
import { ArrowRight } from 'lucide-react';
import { assetUrl, VanteraCopy } from '../i18n/vanteraStrings';

interface PartnershipsSectionProps {
  copy: VanteraCopy;
  onDiscussPartnership: () => void;
}

export const PartnershipsSection: React.FC<PartnershipsSectionProps> = ({
  copy,
  onDiscussPartnership,
}) => {
  return (
    <section className="section-pad partnerships-section reveal">
      <div className="container split-grid">
        <div className="frame-box">
          <img
            src={assetUrl('assets/images/img_business_meeting.jpg')}
            alt={copy.partnershipsHeadline}
            loading="lazy"
          />
        </div>

        <div className="partnerships-copy">
          <div className="eyebrow">{copy.partnershipsEyebrow}</div>
          <h2 className="section-title">{copy.partnershipsHeadline}</h2>
          <p>{copy.partnershipsBody}</p>
          <hr className="partnerships-divider" />
          <button type="button" className="btn btn-navy" onClick={onDiscussPartnership}>
            <span>{copy.partnershipsCta}</span>
            <ArrowRight size={18} className="rtl-flip" aria-hidden="true" />
          </button>
        </div>
      </div>
    </section>
  );
};
