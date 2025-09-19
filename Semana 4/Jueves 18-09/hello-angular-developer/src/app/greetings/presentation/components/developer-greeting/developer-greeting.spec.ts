import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeveloperGreeting } from './developer-greeting';

describe('DeveloperGreeting', () => {
  let component: DeveloperGreeting;
  let fixture: ComponentFixture<DeveloperGreeting>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeveloperGreeting]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeveloperGreeting);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
