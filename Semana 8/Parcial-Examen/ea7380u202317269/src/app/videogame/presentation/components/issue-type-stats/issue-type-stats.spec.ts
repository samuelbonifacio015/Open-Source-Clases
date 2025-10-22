import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IssueTypeStats } from './issue-type-stats';

describe('IssueTypeStats', () => {
  let component: IssueTypeStats;
  let fixture: ComponentFixture<IssueTypeStats>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IssueTypeStats]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IssueTypeStats);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
