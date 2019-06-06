import { DocumentService } from './document.service';
import { Document } from './document';
import { Observable, Subject } from 'rxjs';

@Component({
  selector: 'app-document',
  templateUrl: './document.component.html',
  styleUrls: ['./document.component.css']
})
export class DocumentComponent implements OnInit {
  documents: Document[];
  lastSearch: string;

  constructor(private documentService: DocumentService) { }

  ngOnInit() {    
  }

  search(query: string) {
    this.lastSearch = query;

    this.documentService.getDocuments(query)
        .subscribe(documents => this.documents = documents);
  }
}