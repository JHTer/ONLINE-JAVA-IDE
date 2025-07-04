const EditorPlaceholder: React.FC = () => {
  return (
    <div className="flex flex-col h-full">
      <div className="bg-neutral-800 border-b border-neutral-700 px-3 py-1 text-xs font-semibold">
        Code Editor (coming soon)
      </div>
      <textarea
        className="flex-1 bg-neutral-900 text-foreground p-3 font-mono resize-none outline-none"
        defaultValue={`// Write your Java code here\npublic class Main {\n  public static void main(String[] args) {\n    System.out.println(\"Hello, World!\");\n  }\n}`}
        readOnly
      />
    </div>
  );
};

export default EditorPlaceholder; 