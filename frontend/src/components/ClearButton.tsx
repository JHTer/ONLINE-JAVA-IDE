interface ClearButtonProps {
  onClick: () => void;
}

const ClearButton: React.FC<ClearButtonProps> = ({ onClick }) => (
  <button
    type="button"
    aria-label="Clear output"
    title="Clear output"
    onClick={onClick}
    className="w-7 h-7 flex items-center justify-center bg-neutral-700 hover:bg-neutral-600 rounded ml-2"
  >
    <svg
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 24 24"
      fill="currentColor"
      className="w-3.5 h-3.5 text-white"
    >
      <path d="M6 19h12v2H6v-2zM19 7h-4l-1-1h-4l-1 1H5v2h14V7zM7 10v7h10v-7H7z" />
    </svg>
  </button>
);

export default ClearButton; 