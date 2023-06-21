import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionBankAccountComponent } from './transaction-bank-account.component';

describe('TransferFromBankAccountComponent', () => {
  let component: TransactionBankAccountComponent;
  let fixture: ComponentFixture<TransactionBankAccountComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TransactionBankAccountComponent]
    });
    fixture = TestBed.createComponent(TransactionBankAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
