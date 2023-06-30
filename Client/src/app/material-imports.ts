import { NgModule } from '@angular/core';

import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatRippleModule } from '@angular/material/core';
import { MatListModule } from '@angular/material/list';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatRadioModule } from '@angular/material/radio';

@NgModule({
    exports: [
        MatIconModule,
        MatButtonModule,
        MatToolbarModule,
        MatCardModule,
        MatDividerModule,
        MatRippleModule,
        MatListModule,
        MatCheckboxModule,
        MatInputModule,
        MatFormFieldModule,
        MatRadioModule,
    ]
})
export class MaterialModule { }