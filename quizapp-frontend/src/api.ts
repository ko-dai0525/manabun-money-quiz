import axios from "axios";

// 保存してあるPINを取得
const storedPin = sessionStorage.getItem("adminPin");
if (storedPin) {
  axios.defaults.headers.common["Authorization"] = `Bearer ${storedPin}`;
}

// APIベースのURL
const API_URL = "http://localhost:8080/api/quizzes";

export interface Quiz {
  id: number;
  questionText: string;
  option1: string;
  option2: string;
  option3: string;
  option4: string;
  answer: number;
  explanation: string;
  category: string;
}

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
  const response = await axios.get<Quiz[]>(API_URL, { params: { category } });
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

/**
 * クイズを削除する
 */
export const deleteQuiz = async (id: number): Promise<void> => {
  await axios.delete(`${API_URL}/${id}`);
};
