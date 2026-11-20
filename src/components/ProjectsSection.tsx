import React from 'react';
import { ArrowRight, Info, Maximize2 } from 'lucide-react';
import { assetUrl, VanteraCopy } from '../i18n/vanteraStrings';
import { ProjectConcept } from '../types/vantera';

interface ProjectsSectionProps {
  copy: VanteraCopy;
  onSelectProject: (project: ProjectConcept) => void;
}

export const ProjectsSection: React.FC<ProjectsSectionProps> = ({
  copy,
  onSelectProject,
}) => {
  return (
    <section id="projects" className="section-pad projects-section reveal">
      <div className="container">
        <div className="eyebrow">{copy.projectsEyebrow}</div>
        <h2 className="section-title">{copy.projectsHeadline}</h2>
        <p className="section-subtitle">{copy.projectsSubtitle}</p>

        <div className="disclaimer-banner" role="note">
          <Info size={20} color="#C47752" style={{ flexShrink: 0, marginTop: 2 }} aria-hidden="true" />
          <span>{copy.projectsDisclaimerBanner}</span>
        </div>

        <div className="cards-2-grid">
          {copy.projects.map((project) => (
            <button
              key={project.id}
              type="button"
              className="editorial-card"
              onClick={() => onSelectProject(project)}
            >
              <div className="card-media">
                <img src={assetUrl(project.imagePath)} alt={project.title} loading="lazy" />
                <div className="card-media-scrim" aria-hidden="true" />
                <span className="project-badge-top">{project.conceptTag}</span>
                <span className="project-zoom-icon" aria-hidden="true">
                  <Maximize2 size={16} />
                </span>
                <span className="project-code-label">{project.code}</span>
              </div>

              <div className="card-body">
                <span className="project-division-tag">{project.divisionLabel}</span>
                <h3 className="card-title">{project.title}</h3>
                <p className="card-desc">{project.summary}</p>
                <div className="project-inspect-row">
                  <span>{copy.projectsInspectLabel}</span>
                  <ArrowRight size={16} className="rtl-flip" aria-hidden="true" />
                </div>
              </div>
            </button>
          ))}
        </div>
      </div>
    </section>
  );
};
