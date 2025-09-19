import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SourceItem } from './source-item';

describe('SourceItem', () => {
  let component: SourceItem;
  let fixture: ComponentFixture<SourceItem>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SourceItem]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SourceItem);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
