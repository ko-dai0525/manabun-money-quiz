import axios from "axios";

export interface Quiz {
  id: number;
  questionText: string;
  option1: string;
  option2: string;
  option3: string;
  option4: string;
  answer: number;
  explanation: string;
}

// バックエンドの API エンドポイント（必要に応じて調整）
const API_URL = "http://localhost:8080/api/quizzes";

/**
 * クイズの一覧を取得する
 */
export const fetchQuizzes = async (): Promise<Quiz[]> => {
  const response = await axios.get<Quiz[]>(API_URL);
  return response.data;
};

/**
 * カテゴリー毎のクイズを取得する
 */
export const fetchQuizzesByCategory = async (
  category: string
): Promise<Quiz[]> => {
  const response = await axios.get<Quiz[]>(
    `/api/quizzes?category=${encodeURIComponent(category)}`
  );
  return response.data;
};

/**
 * 新しいクイズを作成する
 * @param quiz - クイズ情報。id は自動生成されるので含めない
 */
export const createQuiz = async (quiz: Omit<Quiz, "id">): Promise<Quiz> => {
  const response = await axios.post<Quiz>(API_URL, quiz);
  return response.data;
};
