import {Component, inject} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {MatButtonToggle, MatButtonToggleGroup} from '@angular/material/button-toggle';

@Component({
  selector: 'app-language-switcher',
  imports: [
    MatButtonToggleGroup,
    MatButtonToggle
  ],
  templateUrl: './language-switcher.html',
  styleUrl: './language-switcher.css'
})
export class LanguageSwitcher {
  protected currentLang: string = 'en';

  /** List  of available languages */
  protected languages: string[] = [ 'en', 'es'];

  /** Translate service */
  private translate: TranslateService;

  /**
   * Creates an instance of LanguageSwitcher.
   * Initializes the current language from the TranslateService.
   */
  constructor() {
    this.translate = inject(TranslateService);
    this.currentLang = this.translate.getCurrentLang();
  }

  /**
   * Changes the application's current language.
   * Updates both the TranslateService and the local currentLang property.
   * @param lang - The language code to switch to (e.g., 'en', 'es').
   */
  protected useLanguage(lang: string): void {
    this.translate.use(lang);
    this.currentLang = lang;
  }
}
