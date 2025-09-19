import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SourceList } from './source-list';

describe('SourceList', () => {
  let component: SourceList;
  let fixture: ComponentFixture<SourceList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SourceList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SourceList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
