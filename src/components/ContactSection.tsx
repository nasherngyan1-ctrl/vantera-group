import React from 'react';
import { ArrowRight, CheckCircle2, Info, Mail, MapPin } from 'lucide-react';
import { VanteraCopy } from '../i18n/vanteraStrings';
import {
  ContactFormErrors,
  ContactFormState,
  ServiceInterest,
  SubmittedInquiry,
} from '../types/vantera';
import { VanteraWordmark } from './Header';

interface ContactSectionProps {
  copy: VanteraCopy;
  formState: ContactFormState;
  formErrors: ContactFormErrors;
  submittedInquiry: SubmittedInquiry | null;
  onChangeField: <K extends keyof ContactFormState>(field: K, value: ContactFormState[K]) => void;
  onSubmit: (e: React.FormEvent) => void;
  onReset: () => void;
}

const SERVICE_KEYS: ServiceInterest[] = [
  'real_estate',
  'construction',
  'general_trading',
  'partnership',
  'general_inquiry',
];

export const ContactSection: React.FC<ContactSectionProps> = ({
  copy,
  formState,
  formErrors,
  submittedInquiry,
  onChangeField,
  onSubmit,
  onReset,
}) => {
  return (
    <section id="contact" className="section-pad contact-section reveal">
      <div className="container">
        <div className="eyebrow">{copy.contactEyebrow}</div>
        <h2 className="section-title">{copy.contactHeadline}</h2>
        <p className="section-subtitle">{copy.contactSubtitle}</p>

        <div className="contact-grid">
          <aside className="contact-info-panel">
            <VanteraWordmark
              wordmark={copy.brandWordmark}
              subword={copy.brandSubword}
              lightOnDark
            />

            <hr className="contact-info-divider" />

            <div className="contact-info-item">
              <MapPin size={22} color="#C47752" style={{ flexShrink: 0, marginTop: 2 }} aria-hidden="true" />
              <div>
                <div className="contact-info-label">{copy.contactLocationTitle}</div>
                <div className="contact-info-value">{copy.contactLocationValue}</div>
              </div>
            </div>

            <div className="contact-info-item">
              <Mail size={22} color="#C47752" style={{ flexShrink: 0, marginTop: 2 }} aria-hidden="true" />
              <div>
                <div className="contact-info-label">{copy.contactEmailTitle}</div>
                <div className="contact-info-value">{copy.contactEmailValue}</div>
                <div className="contact-info-note">{copy.contactEmailDemoNote}</div>
              </div>
            </div>

            <div className="demo-notice-box" role="note">
              <Info size={18} color="#C47752" style={{ flexShrink: 0, marginTop: 2 }} aria-hidden="true" />
              <span>{copy.contactDemoNotice}</span>
            </div>
          </aside>

          <div className="contact-form-panel">
            {submittedInquiry ? (
              <div>
                <div className="success-banner" role="status">
                  <CheckCircle2 size={24} style={{ flexShrink: 0, marginTop: 2 }} aria-hidden="true" />
                  <div>
                    <h3>{copy.successTitle}</h3>
                    <p>{copy.successDemoExplanation}</p>
                  </div>
                </div>

                <div className="summary-box">
                  <div className="summary-heading">{copy.successSummaryLabel}</div>
                  <div>
                    <div className="summary-item-label">{copy.fieldFullName}</div>
                    <div className="summary-item-value">{submittedInquiry.fullName}</div>
                  </div>
                  <div>
                    <div className="summary-item-label">{copy.fieldEmail}</div>
                    <div className="summary-item-value">{submittedInquiry.email}</div>
                  </div>
                  <div>
                    <div className="summary-item-label">{copy.fieldPhone}</div>
                    <div className="summary-item-value">{submittedInquiry.phone}</div>
                  </div>
                  {submittedInquiry.companyName && (
                    <div>
                      <div className="summary-item-label">{copy.fieldCompany}</div>
                      <div className="summary-item-value">{submittedInquiry.companyName}</div>
                    </div>
                  )}
                  <div>
                    <div className="summary-item-label">{copy.fieldServiceInterest}</div>
                    <div className="summary-item-value">{submittedInquiry.serviceLabel}</div>
                  </div>
                  <div>
                    <div className="summary-item-label">{copy.fieldMessage}</div>
                    <div className="summary-item-value">{submittedInquiry.message}</div>
                  </div>
                </div>

                <button
                  type="button"
                  className="btn btn-outline-navy"
                  style={{ width: '100%', marginTop: 18 }}
                  onClick={onReset}
                >
                  {copy.successResetCta}
                </button>
              </div>
            ) : (
              <form className="form-grid" onSubmit={onSubmit} noValidate>
                <div className="field-group">
                  <label className="field-label" htmlFor="vantera-full-name">
                    {copy.fieldFullName}
                  </label>
                  <input
                    id="vantera-full-name"
                    type="text"
                    className={`field-input${formErrors.fullNameError ? ' has-error' : ''}`}
                    value={formState.fullName}
                    onChange={(e) => onChangeField('fullName', e.target.value)}
                    autoComplete="name"
                    aria-invalid={Boolean(formErrors.fullNameError)}
                  />
                  {formErrors.fullNameError && (
                    <span className="field-error-text">{formErrors.fullNameError}</span>
                  )}
                </div>

                <div className="form-row-2">
                  <div className="field-group">
                    <label className="field-label" htmlFor="vantera-email">
                      {copy.fieldEmail}
                    </label>
                    <input
                      id="vantera-email"
                      type="email"
                      className={`field-input${formErrors.emailError ? ' has-error' : ''}`}
                      value={formState.email}
                      onChange={(e) => onChangeField('email', e.target.value)}
                      autoComplete="email"
                      aria-invalid={Boolean(formErrors.emailError)}
                    />
                    {formErrors.emailError && (
                      <span className="field-error-text">{formErrors.emailError}</span>
                    )}
                  </div>

                  <div className="field-group">
                    <label className="field-label" htmlFor="vantera-phone">
                      {copy.fieldPhone}
                    </label>
                    <input
                      id="vantera-phone"
                      type="tel"
                      className={`field-input${formErrors.phoneError ? ' has-error' : ''}`}
                      value={formState.phone}
                      onChange={(e) => onChangeField('phone', e.target.value)}
                      autoComplete="tel"
                      aria-invalid={Boolean(formErrors.phoneError)}
                    />
                    {formErrors.phoneError && (
                      <span className="field-error-text">{formErrors.phoneError}</span>
                    )}
                  </div>
                </div>

                <div className="field-group">
                  <label className="field-label" htmlFor="vantera-company">
                    {copy.fieldCompany} ({copy.fieldCompanyOptional})
                  </label>
                  <input
                    id="vantera-company"
                    type="text"
                    className="field-input"
                    value={formState.companyName}
                    onChange={(e) => onChangeField('companyName', e.target.value)}
                    autoComplete="organization"
                  />
                </div>

                <div className="field-group">
                  <label className="field-label" htmlFor="vantera-service">
                    {copy.fieldServiceInterest}
                  </label>
                  <select
                    id="vantera-service"
                    className="field-select"
                    value={formState.serviceInterest}
                    onChange={(e) =>
                      onChangeField('serviceInterest', e.target.value as ServiceInterest)
                    }
                  >
                    {SERVICE_KEYS.map((key) => (
                      <option key={key} value={key}>
                        {copy.serviceOptions[key]}
                      </option>
                    ))}
                  </select>
                </div>

                <div className="field-group">
                  <label className="field-label" htmlFor="vantera-message">
                    {copy.fieldMessage}
                  </label>
                  <textarea
                    id="vantera-message"
                    rows={4}
                    className={`field-textarea${formErrors.messageError ? ' has-error' : ''}`}
                    value={formState.message}
                    onChange={(e) => onChangeField('message', e.target.value)}
                    aria-invalid={Boolean(formErrors.messageError)}
                  />
                  {formErrors.messageError && (
                    <span className="field-error-text">{formErrors.messageError}</span>
                  )}
                </div>

                <button type="submit" className="btn btn-navy" style={{ width: '100%' }}>
                  <span>{copy.submitInquiryCta}</span>
                  <ArrowRight size={18} className="rtl-flip" aria-hidden="true" />
                </button>
              </form>
            )}
          </div>
        </div>
      </div>
    </section>
  );
};
