import docx

def read_docx(file_path):
    doc = docx.Document(file_path)
    full_text = []
    for para in doc.paragraphs:
        full_text.append(para.text)
    return '\n'.join(full_text)

print("--- Project_Report_AI_Resume_Detailed.docx ---")
try:
    print(read_docx(r"D:\Program-Testing\Project_Report_AI_Resume_Detailed.docx")[:5000]) # Print first 5000 chars
except Exception as e:
    print(f"Error: {e}")
