export type AppLanguage = 'en' | 'ku' | 'ar';

export type NavSectionId = 'home' | 'about' | 'services' | 'projects' | 'contact';

export type DivisionId = 'real_estate' | 'construction' | 'general_trading';

export type ServiceInterest =
  | 'real_estate'
  | 'construction'
  | 'general_trading'
  | 'partnership'
  | 'general_inquiry';

export interface LanguageOption {
  code: AppLanguage;
  nativeLabel: string;
  shortCode: string;
  isRtl: boolean;
}

export interface DivisionItem {
  id: DivisionId;
  numberLabel: string;
  title: string;
  subtitle: string;
  description: string;
  focusAreas: string[];
  serviceInterest: ServiceInterest;
  imagePath: string;
}

export interface ProjectConcept {
  id: string;
  code: string;
  title: string;
  divisionLabel: string;
  conceptTag: string;
  summary: string;
  architecturalNotes: string;
  imagePath: string;
}

export interface WhyChoosePillar {
  indexLabel: string;
  title: string;
  description: string;
}

export interface ContactFormState {
  fullName: string;
  email: string;
  phone: string;
  companyName: string;
  serviceInterest: ServiceInterest;
  message: string;
}

export interface ContactFormErrors {
  fullNameError?: string;
  emailError?: string;
  phoneError?: string;
  messageError?: string;
}

export interface SubmittedInquiry {
  fullName: string;
  email: string;
  phone: string;
  companyName: string;
  serviceLabel: string;
  message: string;
}
