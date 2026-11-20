import React, { useEffect } from 'react';
import { ArrowRight, X } from 'lucide-react';
import { assetUrl, VanteraCopy } from '../i18n/vanteraStrings';
import { DivisionItem, ProjectConcept, ServiceInterest } from '../types/vantera';

interface DivisionModalProps {
  copy: VanteraCopy;
  division: DivisionItem;
  onClose: () => void;
  onInquire: (serviceInterest: ServiceInterest) => void;
}

export const DivisionModal: React.FC<DivisionModalProps> = ({
  copy,
  division,
  onClose,
  onInquire,
}) => {
  useEffect(() => {
    const onKeyDown = (e: KeyboardEvent) => {
      if (e.key === 'Escape') onClose();
    };
    window.addEventListener('keydown', onKeyDown);
    return () => window.removeEventListener('keydown', onKeyDown);
  }, [onClose]);

  return (
    <div
      className="modal-backdrop"
      role="dialog"
      aria-modal="true"
      aria-labelledby="division-modal-title"
      onClick={onClose}
    >
      <div className="modal-card" onClick={(e) => e.stopPropagation()}>
        <div className="modal-media">
          <img src={assetUrl(division.imagePath)} alt={division.title} />
          <button
            type="button"
            className="modal-close-btn"
            aria-label={copy.closeMenuLabel}
            onClick={onClose}
          >
            <X size={20} />
          </button>
          <span
            className="card-index-badge"
            style={{ top: 'auto', bottom: 14, color: '#C47752' }}
          >
            {division.numberLabel} • {division.subtitle}
          </span>
        </div>

        <div className="modal-body">
          <h2 id="division-modal-title" className="section-title" style={{ marginTop: 0 }}>
            {division.title}
          </h2>
          <p style={{ margin: 0, color: '#30343B' }}>{division.description}</p>

          <hr className="partnerships-divider" style={{ marginBlock: 6 }} />

          <div style={{ color: '#C47752', fontWeight: 700, fontSize: '0.88rem' }}>
            {copy.divisionFocusTitle}
          </div>

          <ul className="modal-focus-list">
            {division.focusAreas.map((area) => (
              <li key={area} className="modal-focus-item">
                <span className="modal-focus-bullet" aria-hidden="true" />
                <span>{area}</span>
              </li>
            ))}
          </ul>

          <button
            type="button"
            className="btn btn-navy"
            style={{ width: '100%', marginTop: 8 }}
            onClick={() => onInquire(division.serviceInterest)}
          >
            <span>{copy.divisionInquireCta}</span>
            <ArrowRight size={18} className="rtl-flip" aria-hidden="true" />
          </button>
        </div>
      </div>
    </div>
  );
};

interface ProjectModalProps {
  copy: VanteraCopy;
  project: ProjectConcept;
  onClose: () => void;
}

export const ProjectModal: React.FC<ProjectModalProps> = ({
  copy,
  project,
  onClose,
}) => {
  useEffect(() => {
    const onKeyDown = (e: KeyboardEvent) => {
      if (e.key === 'Escape') onClose();
    };
    window.addEventListener('keydown', onKeyDown);
    return () => window.removeEventListener('keydown', onKeyDown);
  }, [onClose]);

  return (
    <div
      className="modal-backdrop"
      role="dialog"
      aria-modal="true"
      aria-labelledby="project-modal-title"
      onClick={onClose}
    >
      <div className="modal-card" onClick={(e) => e.stopPropagation()}>
        <div className="modal-media">
          <img src={assetUrl(project.imagePath)} alt={project.title} />
          <button
            type="button"
            className="modal-close-btn"
            aria-label={copy.closeMenuLabel}
            onClick={onClose}
          >
            <X size={20} />
          </button>
          <span className="project-badge-top">{copy.projectConceptBadge}</span>
        </div>

        <div className="modal-body">
          <div className="project-division-tag">
            {project.code} • {project.divisionLabel}
          </div>
          <h2 id="project-modal-title" className="section-title" style={{ marginTop: 0 }}>
            {project.title}
          </h2>
          <p style={{ margin: 0, color: '#30343B' }}>{project.summary}</p>

          <div className="modal-notes-box">
            <div style={{ fontWeight: 700, fontSize: '0.84rem', color: '#17243A' }}>
              {copy.projectNotesLabel}
            </div>
            <p style={{ margin: '6px 0 0', fontSize: '0.92rem', color: '#5D636E' }}>
              {project.architecturalNotes}
            </p>
          </div>

          <p style={{ margin: 0, fontSize: '0.8rem', color: '#5D636E' }}>
            {copy.projectsDisclaimerBanner}
          </p>
        </div>
      </div>
    </div>
  );
};
