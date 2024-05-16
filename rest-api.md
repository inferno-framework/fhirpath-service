
# FHIRPath Routes

### Evaluate a FHIRPath expression
- **Route:**
`POST /evaluate`
- **Query Params:**
`path=[FHIRPath expression]` (Required)
- **Body:**
the JSON or XML FHIR element to serve as the root element when evaluating the expression. Accepts all
complex datatypes (i.e. full resources, Elements, BackboneElements) but not primitives.
- **Response:**
a JSON array representing the result of evaluating the given expression against the given root element.
Each "result" in the returned array will be in the form
`{ "type": "[FHIR datatype name]", "element": [JSON representation of element] }`.

