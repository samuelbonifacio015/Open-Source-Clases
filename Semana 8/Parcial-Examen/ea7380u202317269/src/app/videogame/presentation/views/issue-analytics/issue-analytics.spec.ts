import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IssueAnalytics } from './issue-analytics';

describe('IssueAnalytics', () => {
  let component: IssueAnalytics;
  let fixture: ComponentFixture<IssueAnalytics>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IssueAnalytics]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IssueAnalytics);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
