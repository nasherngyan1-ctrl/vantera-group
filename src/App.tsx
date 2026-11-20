import React, { useCallback, useEffect, useMemo, useState } from 'react';
import { getVanteraCopy, LANGUAGE_OPTIONS } from './i18n/vanteraStrings';
import {
  AppLanguage,
  ContactFormErrors,
  ContactFormState,
  DivisionItem,
  NavSectionId,
  ProjectConcept,
  ServiceInterest,
  SubmittedInquiry,
} from './types/vantera';
import { Header } from './components/Header';
import { HeroSection } from './components/HeroSection';
import { AboutSection } from './components/AboutSection';
import { DivisionsSection } from './components/DivisionsSection';
import { ProjectsSection } from './components/ProjectsSection';
import { WhyChooseSection } from './components/WhyChooseSection';
import { ExpertiseSection } from './components/ExpertiseSection';
import { PartnershipsSection } from './components/PartnershipsSection';
import { ContactSection } from './components/ContactSection';
import { FooterSection } from './components/FooterSection';
import { DivisionModal, ProjectModal } from './components/Modals';

const INITIAL_FORM_STATE: ContactFormState = {
  fullName: '',
  email: '',
  phone: '',
  companyName: '',
  serviceInterest: 'real_estate',
  message: '',
};

function validateForm(
  form: ContactFormState,
  copy: ReturnType<typeof getVanteraCopy>,
): ContactFormErrors {
  const errors: ContactFormErrors = {};
  const name = form.fullName.trim();
  const email = form.email.trim();
  const phone = form.phone.trim();
  const message = form.message.trim();

  if (name.length < 2) {
    errors.fullNameError = copy.errorFullNameRequired;
  }
  if (!email) {
    errors.emailError = copy.errorEmailRequired;
  } else if (!/^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/.test(email)) {
    errors.emailError = copy.errorEmailInvalid;
  }

  const digits = phone.replace(/\D/g, '');
  if (!phone) {
    errors.phoneError = copy.errorPhoneRequired;
  } else if (digits.length < 7) {
    errors.phoneError = copy.errorPhoneInvalid;
  }

  if (message.length < 10) {
    errors.messageError = copy.errorMessageRequired;
  }

  return errors;
}

export const App: React.FC = () => {
  const [language, setLanguage] = useState<AppLanguage>('en');
  const [activeSection, setActiveSection] = useState<NavSectionId>('home');
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
  const [selectedDivisionId, setSelectedDivisionId] = useState<DivisionItem['id'] | null>(null);
  const [selectedProjectId, setSelectedProjectId] = useState<string | null>(null);

  const [formState, setFormState] = useState<ContactFormState>(INITIAL_FORM_STATE);
  const [hasAttemptedSubmit, setHasAttemptedSubmit] = useState(false);
  const [submittedInquiry, setSubmittedInquiry] = useState<SubmittedInquiry | null>(null);

  const copy = useMemo(() => getVanteraCopy(language), [language]);

  const selectedDivision = useMemo(
    () => copy.divisions.find((d) => d.id === selectedDivisionId) ?? null,
    [copy, selectedDivisionId],
  );
  const selectedProject = useMemo(
    () => copy.projects.find((p) => p.id === selectedProjectId) ?? null,
    [copy, selectedProjectId],
  );

  const formErrors = useMemo(
    () => (hasAttemptedSubmit ? validateForm(formState, copy) : {}),
    [hasAttemptedSubmit, formState, copy],
  );

  // Sync HTML lang, dir (LTR/RTL), and document title
  useEffect(() => {
    const langMeta = LANGUAGE_OPTIONS.find((item) => item.code === language) ?? LANGUAGE_OPTIONS[0];
    document.documentElement.lang = langMeta.code;
    document.documentElement.dir = langMeta.isRtl ? 'rtl' : 'ltr';
    document.documentElement.setAttribute('data-lang', langMeta.code);
    document.title = copy.pageTitle;
  }, [language, copy.pageTitle]);

  // Subtle entrance reveal observer
  useEffect(() => {
    const elements = Array.from(document.querySelectorAll<HTMLElement>('.reveal'));
    if (!('IntersectionObserver' in window)) {
      elements.forEach((el) => el.classList.add('is-visible'));
      return;
    }

    const observer = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting) {
            entry.target.classList.add('is-visible');
            observer.unobserve(entry.target);
          }
        });
      },
      { threshold: 0.08, rootMargin: '0px 0px -30px 0px' },
    );

    elements.forEach((el) => observer.observe(el));
    return () => observer.disconnect();
  }, []);

  // Scroll-spy for active header navigation item
  useEffect(() => {
    const sectionIds: NavSectionId[] = ['home', 'about', 'services', 'projects', 'contact'];
    const handleScroll = () => {
      const scrollMarker = window.scrollY + 140;
      let current: NavSectionId = 'home';
      for (const id of sectionIds) {
        const el = document.getElementById(id);
        if (el && el.offsetTop <= scrollMarker) {
          current = id;
        }
      }
      setActiveSection(current);
    };

    window.addEventListener('scroll', handleScroll, { passive: true });
    handleScroll();
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  const scrollToSection = useCallback((sectionId: NavSectionId) => {
    setActiveSection(sectionId);
    const target = document.getElementById(sectionId);
    if (target) {
      const headerOffset = 74;
      const top = target.getBoundingClientRect().top + window.scrollY - headerOffset;
      window.scrollTo({ top: Math.max(0, top), behavior: 'smooth' });
    }
  }, []);

  const handleFieldChange = useCallback(
    <K extends keyof ContactFormState>(field: K, value: ContactFormState[K]) => {
      setFormState((prev) => ({ ...prev, [field]: value }));
    },
    [],
  );

  const handleFormSubmit = useCallback(
    (e: React.FormEvent) => {
      e.preventDefault();
      const errors = validateForm(formState, copy);
      if (Object.keys(errors).length > 0) {
        setHasAttemptedSubmit(true);
        return;
      }

      setHasAttemptedSubmit(false);
      setSubmittedInquiry({
        fullName: formState.fullName.trim(),
        email: formState.email.trim(),
        phone: formState.phone.trim(),
        companyName: formState.companyName.trim(),
        serviceLabel: copy.serviceOptions[formState.serviceInterest],
        message: formState.message.trim(),
      });
    },
    [formState, copy],
  );

  const handleFormReset = useCallback(() => {
    setSubmittedInquiry(null);
    setHasAttemptedSubmit(false);
    setFormState((prev) => ({
      ...INITIAL_FORM_STATE,
      serviceInterest: prev.serviceInterest,
    }));
  }, []);

  const handlePreselectServiceAndScroll = useCallback(
    (serviceInterest: ServiceInterest) => {
      setSelectedDivisionId(null);
      setSelectedProjectId(null);
      setIsMobileMenuOpen(false);
      setSubmittedInquiry(null);
      setFormState((prev) => ({ ...prev, serviceInterest }));
      scrollToSection('contact');
    },
    [scrollToSection],
  );

  return (
    <div className="vantera-app">
      <Header
        copy={copy}
        currentLanguage={language}
        activeSection={activeSection}
        isMobileMenuOpen={isMobileMenuOpen}
        onSelectSection={scrollToSection}
        onSelectLanguage={setLanguage}
        onToggleMobileMenu={setIsMobileMenuOpen}
      />

      <main>
        <HeroSection
          copy={copy}
          onExploreBusiness={() => scrollToSection('services')}
          onContactTeam={() => scrollToSection('contact')}
        />
        <AboutSection copy={copy} />
        <DivisionsSection
          copy={copy}
          onSelectDivision={(division) => setSelectedDivisionId(division.id)}
        />
        <ProjectsSection
          copy={copy}
          onSelectProject={(project: ProjectConcept) => setSelectedProjectId(project.id)}
        />
        <WhyChooseSection copy={copy} />
        <ExpertiseSection copy={copy} />
        <PartnershipsSection
          copy={copy}
          onDiscussPartnership={() => handlePreselectServiceAndScroll('partnership')}
        />
        <ContactSection
          copy={copy}
          formState={formState}
          formErrors={formErrors}
          submittedInquiry={submittedInquiry}
          onChangeField={handleFieldChange}
          onSubmit={handleFormSubmit}
          onReset={handleFormReset}
        />
      </main>

      <FooterSection
        copy={copy}
        onSelectSection={scrollToSection}
        onSelectDivision={(division) => setSelectedDivisionId(division.id)}
      />

      {selectedDivision && (
        <DivisionModal
          copy={copy}
          division={selectedDivision}
          onClose={() => setSelectedDivisionId(null)}
          onInquire={handlePreselectServiceAndScroll}
        />
      )}

      {selectedProject && (
        <ProjectModal
          copy={copy}
          project={selectedProject}
          onClose={() => setSelectedProjectId(null)}
        />
      )}
    </div>
  );
};

export default App;
